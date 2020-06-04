<template>
<div class="list">
    <el-scrollbar class="page-component__scroll">
         <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
                <el-breadcrumb-item>Map</el-breadcrumb-item>
            </el-breadcrumb>
            <gmap-map :center="center" :zoom="10" style="width: 100%; height: 750px">
                <gmap-marker :key="index" v-for="(m, index) in markers"
                    :position="m.position" :clickable="true"
                    :draggable="false" @click="center=m.position;openDialog()"
                    :icon="m.markerOptions">
                </gmap-marker>
            </gmap-map>

            <el-dialog title :visible.sync="dialogVisible" width="65%" @close="closeDialog"
                    :before-close="handleClose">
                <live></live>
            </el-dialog>
        </el-main>
    </el-scrollbar>
</div>
</template>
<script>
import Live from './Live.vue'

export default {
    data () {
        let size = {
            width: 60,
            height: 90,
            f: 'px',
            b: 'px'
        }
        let scaledSize = {
            width: 30,
            height: 45,
            f: 'px',
            b: 'px'
        }

        let red = {
            url: './images/red.png',
            size: size,
            scaledSize: scaledSize
        }

        let blue = {
            url: './images/blue.png',
            size: size,
            scaledSize: scaledSize
        }

        let green = {
            url: './images/green.png',
            size: size,
            scaledSize: scaledSize
        }

        let grey = {
            url: './images/grey.png',
            size: size,
            scaledSize: scaledSize
        }

        return {
            center: {
                lat: 40.74,
                lng: -73.93
            },
            markers: [
                {
                    position: {
                        lat: 40.641449,
                        lng: -73.778107
                    },
                    markerOptions: red
                },
                {
                    position: {
                        lat: 40.806670,
                        lng: -73.964777
                    },
                    markerOptions: blue
                },
                {
                    position: {
                        lat: 40.730733,
                        lng: -73.995581
                    },
                    markerOptions: grey
                },
                {
                    position: {
                        lat: 40.805353,
                        lng: -73.110684
                    },
                    markerOptions: green
                },
                {
                    position: {
                        lat: 40.705180,
                        lng: -73.622021
                    },
                    markerOptions: blue
                },
                {
                    position: {
                        lat: 40.737103,
                        lng: -73.671131
                    },
                    markerOptions: red
                }
            ],
            dialogVisible: false
        }
    },

    methods: {
        openDialog () {
            this.dialogVisible = true
        },

        closeDialog () {
            // this.videoUrl = ""
        }
    },

    components: {
        Live
    }
}
</script>
<style lang="scss" scoped>
.player {
    margin-left: auto;
    margin-right: auto;
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
