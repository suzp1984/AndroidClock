cordova.define("cordova-plugin-echo.echo", function(require, exports, module) {

    var cordova = require('cordova'),
        exec = require('cordova/exec');

    var Echo = function () {
    };

    Echo.prototype.echo = function(str, callback) {
        exec(callback, function(err) {
            callback('Nothing to echo.');
        }, "Echo", "echo", [str]);
    };

    var echo = new Echo();

    module.exports = echo;
});