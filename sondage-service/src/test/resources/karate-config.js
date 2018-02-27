function() {
    var port = karate.properties['demo.server.port'];
    if (!port) {
        port = 8080;
    }
    return {
        baseUrl: 'http://localhost:' + port
    }
}
