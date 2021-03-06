import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const Map = () => import(/* webpackChunkName: "index" */ '../components/Map.vue')
const Stats = () => import(/* webpackChunkName: "index" */ '../components/Stats.vue')

const routes = [
    {
        path: '/index',
        name: 'index',
        // component: Live
        redirect: {
            name: 'map'
        }
    },
    {
        path: '/',
        redirect: {
            name: 'map'
        }
    },
    {
        path: '/map',
        name: "map",
        component: Map
    },
    {
        path: '/stats',
        name: "stats",
        component: Stats
    }
]

export default new Router({
    routes,
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return { x: 0, y: 0 }
        }
    },
    strict: process.env.NODE_ENV !== 'production'
})
