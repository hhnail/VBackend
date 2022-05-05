const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/vapi',
        createProxyMiddleware({
            target: 'http://localhost:8080',
            changeOrigin: true,
            secure: false,
            pathRewrite: {
                "^/vapi": "/vapi"
            }
        })
    );
};