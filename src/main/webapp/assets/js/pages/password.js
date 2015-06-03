;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class Register
     * @constructor
     */
    function Password() {
        this.init();
        console.log('password init calling');
    }


    var tool = require("ui-helper.js");

    var clientValid = require("validation.js");


    /**
     * method to init page
     */
    Password.prototype.init = function () {

        $("#form_password").bind("submit", function (e) {
            return checkOldPassword(true) && checkNewPassword(true) && checkPasswordConfirm(true)
                && checkCaptcha(true);
        });

        $("img.captcha-img").bind("click", function (e) {
            this.src = this.src.split('?')[0] + "?_t=" + new Date().getTime();
        });


        $("#txt_old_password").bind("blur", function (e) {
            checkOldPassword();
        });

        $("#txt_new_password").bind("blur", function (e) {
            checkNewPassword();
        });

        $("#txt_passwordConfirm").bind("blur", function (e) {
            checkPasswordConfirm();
        });
        $("#txt_captcha").bind("blur", function (e) {
            checkCaptcha();
        });

        function checkOldPassword(c) {
            var jqCtrl = $("#txt_old_password");
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

        function checkNewPassword(c) {
            var jqCtrl = $("#txt_new_password");
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
            if (jqCtrl.val() !== $("#txt_new_password").val()) {
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


    module.exports = new Password();
});