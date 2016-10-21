var ciaoSella = {
    globals: {
        isAuth: false,
        userDetails: {
            name: null,
            email: null,
            img: null,
            ibCode: null,

        },
        sessionId: null,
        noImage: 'img/logo.png',
        curUser: {
            name: "Sella Bots",
            img: 'img/bots.png',
            imgclass: "pull-left"
        },
        conntectorUrl: {
            login: "http://192.50.51.188:1338/",
            // aiAgent: "http://192.50.51.122:8282/Chatbots/ws/processtext"
            aiAgent: "http://192.50.51.127:8080/chatbots/ws/processtext"
        },
        appversion: "1.0",
        Deviceid: "",
        Platform: "",
        authMode: null,
        authChar1: null,
        authChar2: null,
        query: null,
        parameter: null

    },
    resetGlobals: function() {
        ciaoSella.globals = {
            isAuth: false,
            userDetails: {
                name: null,
                email: null,
                img: null,
                ibCode: null
            },
            sessionId: null,
            noImage: 'img/logo.png',
            curUser: {
                name: "Sella Bots",
                img: 'img/bots.png'
            },
            conntectorUrl: {
                login: "http://192.50.51.188:1338/",
                aiAgent: ""
            },
            appversion: "1.0",
            Deviceid: "",
            Platform: "",
            authMode: null,
            authChar1: null,
            authChar2: null,

        }
    },
    init: function() {

    },

    submitMessage: function() {
        var userMessage = $(".userMessage").val();
        ciaoSella.globals.query = $(".userMessage").val();
        var info = { message: userMessage, action: "msg" };
        ciaoSella.updateUserMessage(info);
        $(".userMessage").val("");
        ciaoSellaTemplates.disableActionBar();
        ciaoSella.postQuery();
    },
    postQuery: function() {
        var okfn = function(resp) {
            console.log(" success resp ", resp);
            ciaoSella.globals.parameter = "";
            ciaoSellaTemplates.enableActionBar();
            ciaoSella.globals.query = resp.request.query;
            ciaoSellaTemplates.updateMessage(resp.response);
        };
        var kofn = function(resp) {
            console.log(" error resp ", resp);
            ciaoSella.globals.parameter = "";
            ciaoSellaTemplates.showMessage("Some Techinical Error. Please try again");
            ciaoSellaTemplates.enableActionBar();

        };
        var reqData = {
            //"isAuthenticate": ciaoSella.globals.isAuth,
            "loginSessionId": ciaoSella.globals.SessionId,
            "query": ciaoSella.globals.query,
            "parameter": ciaoSella.globals.parameter
        }

        ciaoSellaUtils.post(ciaoSella.globals.conntectorUrl.aiAgent, reqData, okfn, kofn);
    },
    updateBotMessage: function(data) {
        ciaoSella.globals.curUser.name = "Sella Bots"
        ciaoSella.globals.curUser.img = "img/bots.png"
        ciaoSella.globals.curUser.imgclass = "pull-left";
        ciaoSellaTemplates.updateMessage(data);
    },
    updateUserMessage: function(data) {
        ciaoSella.globals.curUser.name = ciaoSella.globals.isAuth ? ciaoSella.globals.userDetails.name : "GUEST";
        ciaoSella.globals.curUser.imgclass = "pull-right";
        ciaoSella.globals.curUser.img = ciaoSella.globals.isAuth ? ciaoSella.globals.userDetails.img : ciaoSella.globals.noImage;
        ciaoSellaTemplates.updateMessage(data);
    },
    messageActionButtons: function(oper, value) {
        $(".suggButtons").remove();
        switch (oper) {
            case "login":
                ciaoSellaTemplates.loadLoginPage();
                break;
            case "confirm":
                var info = {
                    message: value,
                    action: "msg"
                }
                ciaoSella.globals.parameter = value;
                ciaoSella.updateUserMessage(info);
                ciaoSella.postQuery();
                break;
            case "suggestion":
                var info = {
                    message: value,
                    action: "msg"
                }
                ciaoSella.globals.parameter = value;
                ciaoSella.updateUserMessage(info);
                ciaoSella.postQuery();
                break;

            case "close":
                break;
        }
    },
    loadLoginSubmit: function() {
        var requestData = {
            function: "FREE/LOGINFIRSTSTEP.SPR",
            codiceinternet: $("input#codice").val(),
            pin: $("input#ibp").val()
        }
        ciaoSella.loginFirstStep(requestData);
    },
    loadLoginDobSubmit: function() {
        var requestData = {
            function: "FREE/LOGINFIRSTSTEPERROR.SPR",
            codiceinternet: $("input#codice").val(),
            pin: $("input#ibp").val(),
            datanascita: $("input#giorno").val() + "/" + $("input#mese").val() + "/" + $("input#anno").val()

        }
        ciaoSella.loginFirstStep(requestData);
    },
    loginFirstStep: function(postdata) {
        var successcb = function(resp) {
            console.log(" loadLoginSubmit successcb ", resp);
            ciaoSella.globals.SessionId = resp.SessionId;
            ciaoSella.globals.ibCode = postdata.codiceinternet;
            ciaoSella.checkPasswordType();
        }
        var failcb = function(resp) {
            console.log(" loadLoginSubmit failcb ", resp);
            ciaoSellaTemplates.loadLoginPageDob();
        }
        ciaoSellaUtils.post(ciaoSella.globals.conntectorUrl.login, postdata, successcb, failcb);
    },

    checkPasswordType: function() {
        var postdata = {
            "function": "FREE/LOGINCHECKPWDTYPE.SPR",
            "codiceinternet": ciaoSella.globals.ibCode,
            "sessionid": ciaoSella.globals.SessionId,
        }

        var successcb = function(resp) {
            ciaoSellaTemplates.checkPasswordType(resp);
        }
        var failcb = function(resp) {
            console.log(" checkPasswordType failcb ", resp);
        }
        ciaoSellaUtils.post(ciaoSella.globals.conntectorUrl.login, postdata, successcb, failcb);
    },
    loginStepTwo: function(postdata) {
        var successcb = function(resp) {
            console.log(" loginStepTwo successcb ", resp);
            ciaoSella.globals.isAuth = true;
            ciaoSella.globals.userDetails.name = ciaoSella.globals.ibCode;
            ciaoSella.globals.Checksum = resp.Checksum;
            localStorage.setItem("Checksum", resp.Checksum)

            $(".headerbutton-logout").show();
            ciaoSellaTemplates.removeModel()
            ciaoSellaTemplates.init();

        }
        var failcb = function(resp) {
            console.log(" loginStepTwo failcb ", resp);
            ciaoSellaTemplates.removeModel()
        }

        ciaoSellaUtils.post(ciaoSella.globals.conntectorUrl.login, postdata, successcb, failcb);
    },
    logout: function() {
        var successcb = function(resp) {
            ciaoSella.resetGlobals();
            ciaoSellaTemplates.loadLoginPage();
        }
        var failcb = function(resp) {
            console.log(" checkPasswordType failcb ", resp);
        }
        postdata = {
            "function": "INFO/LOGOUT.SPR",
            "codiceinternet": ciaoSella.globals.ibCode,
            "sessionid": ciaoSella.globals.SessionId,
        };
        ciaoSellaUtils.post(postdata, successcb, failcb);
    },
    pwdConfirm: function() {
        console.log(" pwdconfirm clicked");
        postdata = {
            "function": "FREE/LOGINSECONDSTEPPWDNOERROR.SPR",
            "codiceinternet": ciaoSella.globals.ibCode,
            "char1": $("input#pwdChar1").val(),
            "char2": $("input#pwdChar2").val(),
            "sessionid": ciaoSella.globals.SessionId,
        };
        ciaoSella.loginStepTwo(postdata);
    },
    otpConfirm: function() {
        var postdata = {
            "function": "FREE/LOGINSECONDSTEPOTPNOERROR.SPR",
            "codiceinternet": ciaoSella.globals.ibCode,
            "otp": $("input#otp").val(),
            "sessionid": ciaoSella.globals.SessionId
        };
        ciaoSella.loginStepTwo(postdata);
    },
}