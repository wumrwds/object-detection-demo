<template>
<div class="list">
    <el-scrollbar class="page-component__scroll">
         <el-main>
             <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
                <el-breadcrumb-item>Live</el-breadcrumb-item>
            </el-breadcrumb>
            <div>
                <videoPlayer class="vjs-custom-skin videoPlayer player"
                    ref="videoPlayer"
                    :options="playerOptions">
                </videoPlayer>
            </div>
        </el-main>
    </el-scrollbar>
</div>
</template>
<script>
import 'video.js/dist/video-js.css'
import {videoPlayer} from 'vue-video-player'
import 'videojs-flash'

export default {
    components: {
        videoPlayer
    },
    props: {
        videoUrl: String,
        height: Number
    },
    data () {
        return {
            playerOptions: {
                height: this.height,
                language: 'en-US',
                sources: [{
                    type: 'rtmp/mp4',
                    src: this.videoUrl
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
    },

    watch: {
        videoUrl: function (val) {
            if (val !== '') {
                this.$refs.videoPlayer.player.src(val)
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
