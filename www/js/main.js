String.prototype.decodeEscapeSequence = function() {
    return this.replace(/\\x([0-9A-Fa-f]{2})/g, function() {
        return String.fromCharCode(parseInt(arguments[1], 16));
    });
};
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.substr(1).toLowerCase();
};
var app = {
    initialize: function() {
        this.bindEvents();
    },
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    onDeviceReady: function() {
        ciaoSellaTemplates.splashScreen();
    },
};

app.initialize();