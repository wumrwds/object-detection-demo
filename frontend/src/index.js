import "babel-polyfill"
import Vue from "vue"
import App from "./components/App.vue"
import store from "./store"
import router from "./routes"
import VueResource from "vue-resource"
import Vuelidate from "vuelidate"
import ElementUI from "element-ui"
import locale from 'element-ui/lib/locale/lang/en'
import "./css/index.scss"
import "element-ui/lib/theme-chalk/index.css"
import VueVideoPlayer from "vue-video-player"
import "video.js/dist/video-js.css"
import * as GmapVue from "gmap-vue"
import HighchartsVue from "highcharts-vue"

require("video.js/dist/video-js.css")
require("vue-video-player/src/custom-theme.css")

Vue.use(VueVideoPlayer)
Vue.use(HighchartsVue)

Vue.use(GmapVue, {
    load: {
        // key: "AIzaSyCSiPl34TU7Y0P2CySY-4z5ZhMcrUZJHxU",
        key: "",
        //   libraries: 'places' // This is required if you use the Autocomplete plugin
        // OR: libraries: 'places,drawing'
        // OR: libraries: 'places,drawing,visualization'
        libraries: "places,drawing,visualization"
        // (as you require)

        // If you want to set the version, you can do so:
        // v: '3.26',
    },

    // If you intend to programmatically custom event listener code
    // (e.g. `this.$refs.gmap.$on('zoom_changed', someFunc)`)
    // instead of going through Vue templates (e.g. `<GmapMap @zoom_changed="someFunc">`)
    // you might need to turn this on.
    // autobindAllEvents: false,

    // If you want to manually install components, e.g.
    // import {GmapMarker} from 'gmap-vue/src/components/marker'
    // Vue.component('GmapMarker', GmapMarker)
    // then set installComponents to 'false'.
    // If you want to automatically install all the components this property must be set to 'true':
    installComponents: true
})

Vue.use(VueResource)
Vue.use(ElementUI, { locale })
Vue.use(Vuelidate)

window.app = new Vue({
    router,
    store,
    render: (h) => h(App)
}).$mount("#app")
