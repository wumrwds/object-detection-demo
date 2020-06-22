import apiPath from './api-path'

const getStats = (vm, option) => {
    return vm.$http.get(apiPath.statsContext, {params: option})
}

const getCamera = (vm, option) => {
    return vm.$http.get(apiPath.cameraContext, {params: option})
}

const utils = {
    getStats,
    getCamera
}

export default utils
