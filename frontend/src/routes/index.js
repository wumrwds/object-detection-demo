import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const Live = () => import(/* webpackChunkName: "index" */ '../components/common/Live.vue')

const routes = [
    {
        path: '/index',
        name: 'index',
        // component: Live
        redirect: {
            name: 'live'
        }
    },
    {
        path: '/',
        redirect: {
            name: 'live'
        }
    },
    {
        path: '/live',
        name: "live",
        component: Live
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
