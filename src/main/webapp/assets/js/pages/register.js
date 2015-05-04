;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class Register
     * @constructor
     */
    function Register() {
        this.init();
        console.log('register init calling');
    }


    var tool = require("ui-helper.js");

    var clientValid = require("validation.js");


    /**
     * method to init page
     */
    Register.prototype.init = function () {

        $("#form_register").bind("submit", function (e) {
            return checkLoginName(true) && checkPassword(true) && checkPasswordConfirm(true)
                && checkCaptcha(true);
        });

        $("img.captcha-img").bind("click", function (e) {
            this.src = this.src.split('?')[0] + "?_t=" + new Date().getTime();
        });


        $("#txt_loginname").bind("blur", function (e) {
            checkLoginName();
        });


        $("#txt_password").bind("blur", function (e) {
            checkPassword();
        });
        $("#txt_passwordConfirm").bind("blur", function (e) {
            checkPasswordConfirm();
        });
        $("#txt_captcha").bind("blur", function (e) {
            checkCaptcha();
        });


        function checkLoginName() {
            var jqCtrl = $("#txt_loginname");
            var loginName = $.trim(jqCtrl.val());
            if (!loginName) {
                jqCtrl.focus();
                return false;
            }

            var ret = clientValid.validate("LoginName", jqCtrl.val());
            if (!ret.success) {
                jqCtrl.focus();
                tool.tooltip(jqCtrl, ret.comment, null, true);
                return false;
            }

            //server validation
            $.getJSON("checkUserName", {userName: loginName},  function (data) {
                if (!data.success) {
                    jqCtrl.focus();
                    tool.tooltip(jqCtrl, data.comment, null, true);
                    return;
                }
            });

            return true;
        }


        function checkPassword(c) {
            var jqCtrl = $("#txt_password");
            if (!$.trim(jqCtrl.val())) {
                if (c) jqCtrl.focus();
                return false;
            }
            //check password format
            var ret = clientValid.validate("Password", jqCtrl.val());
            if (!ret.success) {
                jqCtrl.focus();
                tool.tooltip(jqCtrl, ret.comment, null, true);
                return false;
            }

            return true;
        }

        function checkPasswordConfirm(c) {
            var jqCtrl = $("#txt_passwordConfirm");
            if (!$.trim(jqCtrl.val())) {
                if (c) jqCtrl.focus();
                return false;
            }
            if (jqCtrl.val() !== $("#txt_password").val()) {
                console.log('incorrect password confirm!');
                jqCtrl.focus();
                tool.tooltip(jqCtrl, clientValid.errorMessage.PASSWORD_AGAIN_INVALID, null, true);

                return false;
            }
            return true;
        }


        function checkCaptcha(c) {
            var jqCtrl = $("#txt_captcha");
            var captcha = $.trim(jqCtrl.val());
            if (!captcha) {
                if (c) jqCtrl.focus();
                return false;
            }

            //check captcha format
            if (captcha.length != 5) {
                jqCtrl.focus();
                tool.tooltip(jqCtrl, "验证码格式不正确", null, true);
                return false;
            }

            return true;
        }

    }


    module.exports = new Register();
});