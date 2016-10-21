var ciaoSellaTemplates = {
    splashScreen: function() {
        $(".container-default").hide();
        $.get("templates/splash-screen.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody).appendTo(".splash");
        });
    },
    init: function() {
        $(".spinner").show();
        ciaoSella.globals.query = "Welcome";
        ciaoSellaTemplates.loadChat();
        ciaoSella.postQuery();
    },
    loadChat: function() {
        var info = {
            user: ciaoSella.globals.isAuth ? ciaoSella.globals.userDetails.name.capitalize() : "GUEST".capitalize(),
            time: ciaoSellaUtils.getCurrentDateTime()
        }
        $(".splash").hide();
        $(".container-default").show();
        $("#ciaoSella").html("")
        $.get("templates/home-container.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody, info).appendTo("#ciaoSella");
            $.get("templates/chat-form.html", function(templateBody) {
                var expandedTemplate = $.tmpl(templateBody).appendTo(".portlet-footer");
            });

        });

    },
    updateMessage: function(data) {
        var inputData = data;
        var msg = data.message;
        var lists = "";
        if (data.list && data.list.length > 0) {
            for (i = 0; i < data.list.length; i++) {
                lists += data.list[i] + "\n";
            }
            msg += "\n" + lists;
        }
        var info = {
            img: ciaoSella.globals.curUser.img,
            imgclass: ciaoSella.globals.curUser.imgclass,
            name: ciaoSella.globals.curUser.name.capitalize(),
            time: ciaoSellaUtils.getCurrentTime(),
            message: msg
        }
        $.get("templates/chat-message.html", function(templateBody) {

            $(".showSuggestion").html("");
            var expandedTemplate = $.tmpl(templateBody, info).appendTo(".chat-widget");
            var actionData = [];
            var actions = inputData.action.toLowerCase();
            switch (actions) {
                case "login":
                    actionData.push({ oper: "login", value: "login", className: "btn-warning pull-right" });
                    ciaoSellaTemplates.messageActionButtons(actionData);
                    break;
                case "confirm":
                    actionData.push({ oper: "confirm", value: "no", className: "btn-default btn-danger pull-right mleft5" });
                    actionData.push({ oper: "confirm", value: "yes", className: "btn-default btn-success pull-right mleft5" });
                    ciaoSellaTemplates.messageActionButtons(actionData);
                    break;
                case "suggestion":
                    var suggestions = inputData.suggestions;
                    console.log("suggestions  ", suggestions)
                    for (i = 0; i < suggestions.length; i++) {
                        actionData.push({ oper: "suggestion", value: suggestions[i], className: "btn-default  btn-primary suggBox" });
                    }
                    ciaoSellaTemplates.messageActionButtons(actionData);
                    break;

                case "link":
                    var links = inputData.link;
                    var data = [];
                    for (i = 0; i < links.length; i++) {
                        actionData.push({ oper: "links", value: links[i], className: "" });
                    }
                    ciaoSellaTemplates.messageActionLinks(actionData);
                    break;
                case "close":
                    break;
            }
            $('.panel-collapse').animate({ scrollTop: $(".chat-widget").height() }, 'slow');
        });
    },
    messageActionLinks: function(data) {
        console.log("data ", data);
        $.get("templates/chat-message-links.html", function(templateBody) {
            $(".showSuggestion").html("");
            var expandedTemplate = $.template(templateBody);
            $.template("listTemplate", expandedTemplate);
            $.tmpl("listTemplate", data).appendTo(".showSuggestion:last");
            $('.panel-collapse').animate({ scrollTop: $(".chat-widget").height() }, 'slow');
        });
    },
    messageActionButtons: function(data) {
        $.get("templates/chat-message-actions.html", function(templateBody) {
            $(".showSuggestion").html("");
            var expandedTemplate = $.template(templateBody);
            $.template("listTemplate", expandedTemplate);
            $.tmpl("listTemplate", data).appendTo(".showSuggestion:last");
            $('.panel-collapse').animate({scrollTop:$(".chat-widget").height()}, 'slow');
        });
    },
    disableActionBar: function() {
        $(".portlet-footer .userMessage").attr("disabled", "disabled");
        $(".portlet-footer button").attr("disabled", "disabled");
    },
    enableActionBar: function() {
        $(".portlet-footer .userMessage").removeAttr("disabled");
        $(".portlet-footer button").removeAttr("disabled");
    },
    loadLoginPage: function() {
        $("#ciaoSella").html("");
        $(".headerbutton-logout").hide();
        $.get("templates/loginpage.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody).appendTo("#ciaoSella");
        });
        $(".titlebox").html("Autenticazione");
        $(".spinner,.spinnerlabel").hide();
    },
    loadLoginPageDob: function() {
        $("#ciaoSella").html("");
        $.get("templates/loginwithdob.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody).appendTo("#ciaoSella");
        });
        $(".titlebox").html("Autenticazione");
        $(".spinner,.spinnerlabel").hide();
    },

    checkPasswordType: function(resp) {
        ciaoSella.globals.authMode = resp.TypeNextStep;
        ciaoSella.globals.authChar1 = resp.Char1;
        ciaoSella.globals.authChar2 = resp.Char2;
        ciaoSella.globals.authMode == "PWD" ? ciaoSellaTemplates.askPassword() : ciaoSellaTemplates.askOtp();
    },
    askPassword: function() {
        $.get("templates/model-pwd.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody, { char1: ciaoSella.globals.authChar1, char2: ciaoSella.globals.authChar1 }).appendTo("#ciaoSella");
        });
    },
    removeModel: function() {
        $(".modal-outer").remove();
    },


    askOtp: function() {
        $.get("templates/model-otp.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody).appendTo("#ciaoSella");
        });
    },

    showMessage: function(msg) {
        $.get("templates/errormessage.html", function(templateBody) {
            var expandedTemplate = $.tmpl(templateBody, { errmsg: msg }).appendTo("#ciaoSella");
        });
    },
}