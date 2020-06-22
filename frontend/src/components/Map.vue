<template>
<div class="list">
    <el-scrollbar class="page-component__scroll">
         <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
                <el-breadcrumb-item>Map</el-breadcrumb-item>
            </el-breadcrumb>

            <div class="title-section">
                <GmapMap :center="center" :zoom="10" style="width: 100%; height: 600px">
                    <GmapMarker :key="index" v-for="(marker, index) in markers"
                        :position="marker.position" :clickable="true"
                        :draggable="false" @click="openInfoWindow(marker, index)"
                        :icon="marker.markerOptions" :label="marker.labelOptions">
                    </GmapMarker>

                    <gmap-info-window
                        v-if="infoWindowPos !== null"
                        :options="infoOptions"
                        :position="infoWindowPos" :opened="infoWinOpen"
                        @closeclick="infoWinOpen = false;"
                    >
                        <div>
                            <el-row>
                                <el-col :span="100">
                                    <strong>Camera Label: </strong> {{ markers[currentMarkerIdx].name }}
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <strong>Vehicles Passed Last Minute: </strong> {{ markers[currentMarkerIdx].vehicleNum }}
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <strong>Pedestrians Passed Last Minute: </strong> {{ markers[currentMarkerIdx].pedestrianNum }}
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <el-button type="primary" plain size="medium" @click="openVideoDialog()">Show Video Stream</el-button>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <el-button type="primary" plain size="medium" @click="openStatsDialog()">Show Historical Stats</el-button>
                                </el-col>
                            </el-row>
                        </div>
                    </gmap-info-window>
                </GmapMap>
            </div>

            <el-dialog title="Video Stream" :visible.sync="videoDialogVisible" width="65%" @close="closeVideoDialog" v-if="videoDialogVisible">
                <live :videoUrl="videoUrl" :height="500"></live>
            </el-dialog>

            <el-dialog title="Chart" class="dialogClass" :visible.sync="statsDialogVisible" width="1375px" @close="closeStatsDialog">
                <chart-base isCameraDisabled ref="chartBase"></chart-base>
            </el-dialog>
        </el-main>
    </el-scrollbar>
</div>
</template>
<script>
import Live from './common/Live.vue'
import ChartBase from './common/ChartBase.vue'
import apiUtil from '@/api/api-utils'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
    data () {
        return {
            center: {
                lat: 40.697475,
                lng: -73.852881
            },
            markers: [],
            // info window
            infoWinOpen: false,
            infoWindowPos: null,
            currentMarkerIdx: null,
            infoOptions: {
                // optional: offset infowindow so it visually sits nicely on top of our marker
                pixelOffset: {
                    width: 0,
                    height: -50
                }
            },
            // video dialog
            videoDialogVisible: false,
            videoUrl: '',
            // stats dialog
            statsDialogVisible: false,
            // websocket
            stompClient: null
        }
    },

    methods: {
        queryCameras () {
            apiUtil.getCamera(this).then((res) => {
                var _self = this
                this.markers = res.body.map(function (obj) {
                    return _self.$options.methods.genMarkerOpt(obj.id, obj.name, obj.lat, obj.lng, obj.vehicleNum, obj.pedestrianNum)
                })
            }, (err) => {
                this.$message.error(err.status)
                this.$message.error(err.body)
            })
        },

        genMarkerOpt (id, name, lat, lng, vehicleNum, pedestrianNum) {
            let markerUrl
            if (!vehicleNum) {
                markerUrl = "./images/gray.png"
            } else if (vehicleNum < 300) {
                markerUrl = "./images/green.png"
            } else if (vehicleNum < 650) {
                markerUrl = "./images/blue.png"
            } else {
                markerUrl = "./images/red.png"
            }

            return {
                id: id,
                name: name,
                position: {
                    lat: lat,
                    lng: lng
                },
                markerOptions: {
                    url: markerUrl,
                    size: {width: 60, height: 90, f: 'px', b: 'px'},
                    scaledSize: {width: 30, height: 45, f: 'px', b: 'px'},
                    anchor: {x: 15, y: 45},
                    labelOrigin: {x: 15, y: -10}
                },
                vehicleNum: vehicleNum,
                pedestrianNum: pedestrianNum,
                labelOptions: {
                    color: 'gray',
                    fontWeight: 'bold',
                    text: name
                }
            }
        },

        openInfoWindow (marker, idx) {
            this.center = marker.position
            this.infoWindowPos = marker.position

            // check if its the same marker that was selected if yes toggle
            if (this.currentMarkerIdx === idx) {
                this.infoWinOpen = !this.infoWinOpen
            } else {
                // if different marker set infowindow to open and reset current marker index
                this.infoWinOpen = true
                this.currentMarkerIdx = idx
            }
        },

        closeInfoWindow () {
            this.infoWinOpen = false
        },

        openVideoDialog () {
            this.videoUrl = 'rtmp://127.0.0.1:1935/live/test'
            this.videoDialogVisible = true
        },

        closeVideoDialog () {
            this.videoUrl = ''
            this.videoDialogVisible = false
        },

        openStatsDialog () {
            this.statsDialogVisible = true
            this.$nextTick(() => {
                this.$refs.chartBase.selectedCameras = [this.markers[currentMarkerIdx].id]
                this.$refs.chartBase.queryStats()
            })
        },

        closeStatsDialog () {
            this.statsDialogVisible = false
        },

        initWebSocket () {
            this.connect()
        },
        connect () {
            const socket = new SockJS('/camera')
            this.stompClient = Stomp.over(socket)
            this.stompClient.connect({}, (frame) => {
                var _self = this
                this.stompClient.subscribe('/topic/camera', (res) => {
                    this.markers = JSON.parse(res.body).map(function (obj) {
                        return _self.$options.methods.genMarkerOpt(obj.id, obj.name, obj.lat, obj.lng, obj.vehicleNum, obj.pedestrianNum)
                    })
                })
            })
        },
        disconnect () {
            if (this.stompClient != null) {
                this.stompClient.disconnect()
                console.log("Disconnected")
            }
        }
    },

    mounted () {
        this.queryCameras()

        if ('WebSocket' in window) {
            this.initWebSocket()
        } else {
            alert('Current browser doesn\'t support Websocket!')
        }
    },

    components: {
        Live,
        ChartBase
    }
}
</script>
<style lang="scss" scoped>
.player {
    margin-left: auto;
    margin-right: auto;
}

.el-row {
    &:first-child {
      margin-top: 10px;
    }
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
}

.el-button{
    width: 250px;
    a{
        text-decoration: none;
        color: #fff;
    }
}

.list{
    height: 100%;
    color: #48576a;
    .header-box {
		background-image: linear-gradient(-180deg, rgba(37,30,71,.9) 0%, rgba(36,83,155,.9) 100%);
		@media (max-width: 1249px) {
            width: 1080px !important;
            margin: 0 auto;
        }
    }
    .page-component__scroll{
        height: 100%;
        .el-breadcrumb{
            padding-bottom: 20px;
            border-bottom: 1px solid #ebeef5;
        }
    }
    .el-main{
        .el-breadcrumb{
            padding-bottom: 20px;
            border-bottom: 1px solid #ebeef5;
        }
        .title-section{
            background: #f0f2f5;
            padding: 10px;
            margin: 20px 0;
            position: relative;
            color: #606266;
            .item-group{
                display: inline-block;
                margin-right: 16px;
                label{
                    padding-right: 6px;
                }
            }
            .el-radio-button__inner{
                padding: 10px
            }
            .el-input{
                width: 230px;
            }
        }
        .query-button{
            position: absolute;
            right:90px;
        }
        .add-button{
            position: absolute;
            right: 10px;
        }
        .el-button{
            a{
                text-decoration: none;
                color: #fff;
            }
            &:hover{

            }
        }
        .el-table{
            color: #606266;
            thead{
                 color: #606266;
            }
        }
        .el-pagination{
            margin-top:30px;
            text-align: right;
        }
    }
}
</style>
<style>
.el-dialog__body {
    padding: 0px 20px 40px 20px;
}
</style>
