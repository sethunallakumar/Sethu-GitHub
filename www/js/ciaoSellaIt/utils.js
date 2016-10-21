var ciaoSellaUtils = {
    getCurrentDateTime: function() {
        var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        var today = new Date();
        var day = today.getDay();
        var dd = today.getDate();
        var mm = today.getMonth();
        var yyyy = today.getFullYear();
        if (dd < 10) dd = '0' + dd
        if (mm < 10) mm = '0' + mm

        return months[parseInt(mm)] + " " + dd + ", " + yyyy + " at " + new Date().toLocaleTimeString();
    },
    getCurrentTime: function() {
        return new Date().toLocaleTimeString();
    },
    getCurrentDate: function() {
        return new Date().toLocaleDateString();
    },
    isJsonParsable: function(data) {
        try {
            objectVal = JSON.parse(data);
            return true;
        } catch (e) {
            return false;
        }
    },
    respDecode: function(arr) {
        var parts = [];
        var is_list = (Object.prototype.toString.apply(arr) === '[object Array]');
        for (var key in arr) {
            var value = arr[key];
            if (!value) {
                parts.push('"' + key + '":' + '"' + value + '"');
            } else if (typeof value == "object") {
                if (is_list) parts.push(this.respDecode(value));
                else parts.push('"' + key + '":' + this.respDecode(value));
            } else {
                var str = "";
                if (!is_list) str = '"' + key + '":';
                if (typeof value == "number") str += value;
                else if (value === false) str += 'false';
                else if (value === true) str += 'true';
                else {
                    var val;
                    val = value.decodeEscapeSequence();
                    if (ciaoSellaUtils.isJsonParsable(val)) {
                        val = (val).replace(/["]/g, '\\"');
                    }
                    val = (val).replace(/(\r\n|\n|\r)/gm, "");
                    str += '"' + val + '"';
                }
                parts.push(str);
            }
        }
        var json = parts.join(",");
        if (is_list) return '[' + json + ']';
        return '{' + json + '}';
    },
    post: function(url, inputdata, successcb, failcb) {
        // inputdata["appversion"] = ciaoSella.globals.appversion;
        // inputdata["Deviceid"] = ciaoSella.globals.Deviceid;
        // inputdata["Platform"] = ciaoSella.globals.Platform;
        inputdata["sessionid"] = ciaoSella.globals.SessionId;
        ciaoSella.globals.curUser.name = "Sella Bots";
        ciaoSella.globals.curUser.imgclass = "pull-left";
        var onSuccess = function(response) {
            var resp = JSON.parse(ciaoSellaUtils.respDecode(response));
            var errMsg = Array.isArray(resp.ErrorMessage) ? resp.ErrorMessage[0].errorMessage :
                (typeof resp.ErrorMessage === "object" ? resp.ErrorMessage.errorMessage : resp.ErrorMessage);
            var errCode = Array.isArray(resp.ErrorMessage) ? resp.ErrorMessage[0].errorCode :
                (typeof resp.ErrorMessage === "object" ? resp.ErrorMessage.errorCode : '');
            if (errMsg && errMsg != "null") {
                ciaoSellaTemplates.showMessage(errMsg);
                failcb(response)
            } else {
                console.log("success");
                successcb(response)
            }
            $(".spinner").hide();
        }
        var onError = function(reponse) {
            console.log("error occured");
            $(".spinner").hide();
            failcb(reponse)
        }

        var data = {};
        data.inputdata = inputdata;
        data.onSuccess = onSuccess;
        data.onError = onError;
        data.connectorUrl = url;
        this.restService(data);

    },
    restService: function(data) {
        $.ajax({
            url: data.connectorUrl,
            data: JSON.stringify(data.inputdata),
            dataType: 'json',
            type: 'POST',
            headers: {
                //     'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: data.onSuccess,
            error: data.onError,
            cache: false
        })
    }
}