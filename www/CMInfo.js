var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

channel.createSticky('onCordovaReadytheCMIF');
// Tell cordova channel to wait on the CordovaInfoReady event
channel.waitForInitialization('onCordovaReadytheCMIF');

function CMInfo() {
    this.available = false;
    this.device = null;
    this.sysVersion = null;
    this.customername = null;
    this.uniqueId = null;
    this.factory = null;
    this.appVersion = null;
    this.appVersionName = null;
    var me = this;

    channel.onCordovaReady.subscribe(function() {
        me.getInfo(function(info) {
            me.available = true;
            me.device = info.device;
            me.sysVersion = info.sysVersion;
            me.customername = info.customername;
            me.uniqueId = info.uniqueId;
            me.factory = info.factory;
            me.appVersion = info.appVersion;
            me.appVersionName = info.appVersionName;
            channel.onCordovaReadytheCMIF.fire();
        }, function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}


CMInfo.prototype.getInfo = function(successCallback, errorCallback) {
    argscheck.checkArgs('fF', 'CMInfo.getInfo', arguments);
    exec(successCallback, errorCallback, "CMInfo", "getCommonInfo", []);
};

CMInfo.prototype.exitApp = function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, "CMInfo", "exitApp", []);
};

module.exports = new CMInfo();