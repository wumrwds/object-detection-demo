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
                                <el-col :span="100">
                                    <strong>Camera Label: </strong> {{ selectedMarkerName }}
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <strong>Vehicles Passed Last Hour: </strong> {{ infoText.vehicleNum }}
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="100">
                                    <strong>Pedestrians Passed Last Hour: </strong> {{ infoText.PedestrianNum }}
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
                </gmap-map>
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
                    id: 'nyc_1',
                    name: 'NYC Camera 1',
                    position: {
                        lat: 40.641449,
                        lng: -73.778107
                    },
                    markerOptions: redMarkerOptions,
                    infoText: {
                        vehicleNum: 61232,
                        PedestrianNum: 2132
                    }
                },
                {
                    id: 'nyc_2',
                    name: 'NYC Camera 2',
                    position: {
                        lat: 40.806670,
                        lng: -73.964777
                    },
                    markerOptions: blueMarkerOptions,
                    infoText: {
                        vehicleNum: 12123,
                        PedestrianNum: 3513
                    }
                },
                {
                    id: 'nyc_3',
                    name: 'NYC Camera 3',
                    position: {
                        lat: 40.730733,
                        lng: -73.995581
                    },
                    markerOptions: greyMarkerOptions,
                    infoText: {
                        vehicleNum: 22283,
                        PedestrianNum: 7872
                    }
                },
                {
                    id: 'nyc_4',
                    name: 'NYC Camera 4',
                    position: {
                        lat: 40.805353,
                        lng: -73.110684
                    },
                    markerOptions: greenMarkerOptions,
                    infoText: {
                        vehicleNum: 9542,
                        PedestrianNum: 7329
                    }
                },
                {
                    id: 'nyc_5',
                    name: 'NYC Camera 5',
                    position: {
                        lat: 40.705180,
                        lng: -73.622021
                    },
                    markerOptions: blueMarkerOptions,
                    infoText: {
                        vehicleNum: 9628,
                        PedestrianNum: 898
                    }
                },
                {
                    id: 'nyc_6',
                    name: 'NYC Camera 6',
                    position: {
                        lat: 40.737103,
                        lng: -73.671131
                    },
                    markerOptions: redMarkerOptions,
                    infoText: {
                        vehicleNum: 4598,
                        PedestrianNum: 1204
                    }
                }
            ],
            selectedMarkerId: '',
            selectedMarkerName: '',
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
            this.selectedMarkerId = marker.id
            this.selectedMarkerName = marker.name

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
            this.$nextTick(() => {
                this.$refs.chartBase.selectedCameras = [this.selectedMarkerId]
                this.$refs.chartBase.queryStats()
            })
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
