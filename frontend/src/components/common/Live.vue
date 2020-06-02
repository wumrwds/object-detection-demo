<template>
  <div>
    <videoPlayer class="vjs-custom-skin videoPlayer myplayer" :options="playerOptions"></videoPlayer>
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
    data () {
        return {
            playerOptions: {
                height: '700',
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
.myplayer {
    margin-left: auto;
    margin-right: auto;
}
</style>
