import apiPath from './api-path'

const getStats = (vm, option) => {
    return vm.$http.get(apiPath.statsContext, {params: option})
}

const getCamera = (vm, option) => {
    return vm.$http.get(apiPath.cameraContext, {params: option})
}

const getJobProcess = (vm, jobId) => {
    return vm.$http.get(`${apiPath.jobContext}/${jobId}/process`)
}

const getErrorLogs = (vm, jobId, option) => {
    return vm.$http.get(`${apiPath.jobContext}/${jobId}/errors`, {params: option})
}

const utils = {
    getStats,
    getCamera,
    getJobProcess,
    getErrorLogs
}

export default utils
