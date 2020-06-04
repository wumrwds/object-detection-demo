<template>
    <!-- <el-container> -->
        <!-- <el-header>
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
                <el-breadcrumb-item>promotion management</el-breadcrumb-item>
                <el-breadcrumb-item>promotion list</el-breadcrumb-item>
                <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
            </el-breadcrumb>
        </el-header> -->
        <!-- <el-main> -->
            <div>
                <videoPlayer class="vjs-custom-skin videoPlayer player" :options="playerOptions"></videoPlayer>
            </div>
        <!-- </el-main> -->
    <!-- </el-container> -->
</template>
<script>
import 'video.js/dist/video-js.css'
import {videoPlayer} from 'vue-video-player'
import 'videojs-flash'

export default {
    components: {
        videoPlayer
    },
    data () {
        return {
            playerOptions: {
                height: '600',
                language: 'en-US',
                sources: [{
                    type: 'rtmp/mp4',
                    src: 'rtmp://127.0.0.1:1935/live/test'
                }],
                techOrder: ['flash', 'html5'],
                autoplay: true,
                controls: true,
                poster: '',
                controlBar: {
                    timeDivider: true,
                    durationDisplay: true,
                    remainingTimeDisplay: false,
                    fullscreenToggle: true
                }
            }
        }
    }
}
</script>
<style lang="scss" scoped>
.player {
    margin-left: auto;
    margin-right: auto;
}

.el-breadcrumb{
            padding-bottom: 20px;
            border-bottom: 1px solid #ebeef5;
        }
</style>
