const merge = require('webpack-merge')
const uglifyJSPlugin = require('uglifyjs-webpack-plugin')
const common = require('./webpack.common')

module.exports = merge(common, {
    devtool: 'source-map',
    plugins: [
        new uglifyJSPlugin({
            sourceMap: true
        })
    ]
})
