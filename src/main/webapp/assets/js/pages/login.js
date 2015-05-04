;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class Register
     * @constructor
     */
    function Login() {
        this.init();
        console.log('login init calling');
    }


    var tool = require("ui-helper.js");


    /**
     * method to init page
     */
    Login.prototype.init = function () {

        $("#form_login").bind("submit", function (e) {
            return checkLoginName(true) && checkPassword(true) && checkCaptcha(true);
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
            return true;
        }


        function checkPassword(c) {
            var jqCtrl = $("#txt_password");
            if (!$.trim(jqCtrl.val())) {
                if (c) jqCtrl.focus();
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


    module.exports = new Login();
});