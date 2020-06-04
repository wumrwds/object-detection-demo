<template>
    <el-container>
        <el-header>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
                <el-breadcrumb-item>promotion management</el-breadcrumb-item>
                <el-breadcrumb-item>promotion list</el-breadcrumb-item>
                <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
            </el-breadcrumb>
        </el-header>
        <el-main>
            <div>
                <gmap-map :center="center" :zoom="12" style="width: 100%; height: 650px">
                    <gmap-marker :key="index" v-for="(m, index) in markers"
                        :position="m.position" :clickable="true"
                        :draggable="false" @click="center=m.position;playVideo()"
                        :icon="m.markerOptions">
                    </gmap-marker>
                </gmap-map>
            </div>
        </el-main>
    </el-container>
</template>
<script>
export default {
    data () {
        let red = {
            url: './images/red.png',
            size: {
                width: 60,
                height: 90,
                f: 'px',
                b: 'px'
            },
            scaledSize: {
                width: 30,
                height: 45,
                f: 'px',
                b: 'px'
            }
        }

        let blue = {
            url: './images/blue.png',
            size: {
                width: 60,
                height: 90,
                f: 'px',
                b: 'px'
            },
            scaledSize: {
                width: 30,
                height: 45,
                f: 'px',
                b: 'px'
            }
        }

        let green = {
            url: './images/green.png',
            size: {
                width: 60,
                height: 90,
                f: 'px',
                b: 'px'
            },
            scaledSize: {
                width: 30,
                height: 45,
                f: 'px',
                b: 'px'
            }
        }

        let grey = {
            url: './images/grey.png',
            size: {
                width: 60,
                height: 90,
                f: 'px',
                b: 'px'
            },
            scaledSize: {
                width: 30,
                height: 45,
                f: 'px',
                b: 'px'
            }
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
            ]
        }
    },

    methods: {

    },

    components: {
    }
}
</script>
<style lang="scss" scoped>
.player {
    margin-left: auto;
    margin-right: auto;
}

.el-breadcrumb {
    padding-bottom: 20px;
    border-bottom: 1px solid #ebeef5;
}
</style>
