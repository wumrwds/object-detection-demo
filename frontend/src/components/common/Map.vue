<template>
<div class="list">
    <el-scrollbar class="page-component__scroll">
         <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
                <el-breadcrumb-item>Map</el-breadcrumb-item>
            </el-breadcrumb>

            <div class="title-section">
                <gmap-map :center="center" :zoom="10" style="width: 100%; height: 600px">
                    <gmap-marker :key="index" v-for="(marker, index) in markers"
                        :position="marker.position" :clickable="true"
                        :draggable="false" @click="openInfoWindow(marker, index)"
                        :icon="marker.markerOptions">
                    </gmap-marker>

                    <gmap-info-window
                        v-if="infoWindowPos !== null"
                        :options="infoOptions"
                        :position="infoWindowPos" :opened="infoWinOpen"
                        @closeclick="infoWinOpen = false;"
                    >
                        <div>
                            <el-row>
                                <el-col :span="24">
                                    <p><strong>Vehicles Passed Last Hour: </strong> {{ infoText.vehicleNum }}</p>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                    <el-button type="primary" plain size="medium" @click="openVideoDialog()">Show Video Stream</el-button>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                    <el-button type="primary" plain size="medium" @click="openStatsDialog()">Show Historical Stats</el-button>
                                </el-col>
                            </el-row>
                        </div>
                    </gmap-info-window>
                </gmap-map>
            </div>

            <el-dialog title :visible.sync="videoDialogVisible" width="65%" @close="closeVideoDialog" v-if="videoDialogVisible">
                <live :videoUrl="videoUrl" height="500"></live>
            </el-dialog>

            <el-dialog title :visible.sync="statsDialogVisible" width="1375px" @close="closeStatsDialog" v-if="statsDialogVisible">
                <chart-base></chart-base>
            </el-dialog>
        </el-main>
    </el-scrollbar>
</div>
</template>
<script>
import Live from './Live.vue'
import ChartBase from './ChartBase.vue'

let redMarkerOptions = {
    url: "./images/red.png",
    size: {width: 60, height: 90, f: 'px', b: 'px'},
    scaledSize: {width: 30, height: 45, f: 'px', b: 'px'},
    anchor: {x: 15, y: 45}
}

let blueMarkerOptions = {
    url: "./images/blue.png",
    size: {width: 60, height: 90, f: 'px', b: 'px'},
    scaledSize: {width: 30, height: 45, f: 'px', b: 'px'},
    anchor: {x: 15, y: 45}
}

let greenMarkerOptions = {
    url: "./images/green.png",
    size: {width: 60, height: 90, f: 'px', b: 'px'},
    scaledSize: {width: 30, height: 45, f: 'px', b: 'px'},
    anchor: {x: 15, y: 45}
}

let greyMarkerOptions = {
    url: "./images/grey.png",
    size: {width: 60, height: 90, f: 'px', b: 'px'},
    scaledSize: {width: 30, height: 45, f: 'px', b: 'px'},
    anchor: {x: 15, y: 45}
}

export default {
    data () {
        return {
            center: {
                lat: 40.697475,
                lng: -73.852881
            },
            markers: [
                {
                    position: {
                        lat: 40.641449,
                        lng: -73.778107
                    },
                    markerOptions: redMarkerOptions,
                    infoText: {
                        vehicleNum: 6
                    }
                },
                {
                    position: {
                        lat: 40.806670,
                        lng: -73.964777
                    },
                    markerOptions: blueMarkerOptions,
                    infoText: {
                        vehicleNum: 6
                    }
                },
                {
                    position: {
                        lat: 40.730733,
                        lng: -73.995581
                    },
                    markerOptions: greyMarkerOptions,
                    infoText: {
                        vehicleNum: 11
                    }
                },
                {
                    position: {
                        lat: 40.805353,
                        lng: -73.110684
                    },
                    markerOptions: greenMarkerOptions,
                    infoText: {
                        vehicleNum: 23
                    }
                },
                {
                    position: {
                        lat: 40.705180,
                        lng: -73.622021
                    },
                    markerOptions: blueMarkerOptions,
                    infoText: {
                        vehicleNum: 82
                    }
                },
                {
                    position: {
                        lat: 40.737103,
                        lng: -73.671131
                    },
                    markerOptions: redMarkerOptions,
                    infoText: {
                        vehicleNum: 36
                    }
                }
            ],
            // info window
            infoWinOpen: false,
            infoWindowPos: null,
            infoText: null,
            currentMidx: null,
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
            statsDialogVisible: false
        }
    },

    methods: {
        openInfoWindow (marker, idx) {
            this.center = marker.position
            this.infoWindowPos = marker.position
            this.infoText = marker.infoText

            // check if its the same marker that was selected if yes toggle
            if (this.currentMidx === idx) {
                this.infoWinOpen = !this.infoWinOpen
            } else {
                // if different marker set infowindow to open and reset current marker index
                this.infoWinOpen = true
                this.currentMidx = idx
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
        },

        closeStatsDialog () {
            this.statsDialogVisible = false
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
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
}

.el-button{
    width: 100%;
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
