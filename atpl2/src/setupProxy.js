const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/Airtel-CongoB',
    createProxyMiddleware({
      target: 'http://192.168.167.73:8083',
      changeOrigin: true,
    })
  );
};