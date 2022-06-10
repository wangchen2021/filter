const px2rem = require('postcss-px2rem')
const postcss = px2rem({
    remUnit: 192 // 基准值
})
module.exports = {
    css: {
        loaderOptions: {
            postcss: {
                plugins: [
                    postcss
                ]
            }
        }
    },
    devServer: {
        port: 3000,
        proxy: {
            '/app': {
                target: 'http://localhost:7000',
                pathRewrite: { '^/app': '' },
                ws: true,
                changeOrigin: true
            },
        }
    },
}