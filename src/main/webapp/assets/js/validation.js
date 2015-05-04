/**
 *
 * Client side validation
 */
;
define(function (require, exports, module) {
    /**
     * @class Validation in client side
     * @constructor
     */
    function Validation() {
        this.errorMessage = {
            PASSWORD_NULL: '请填写密码',
            PASSWORD_LENGTH: '请填写至少 6 位密码',
            PASSWORD_AGAIN_NULL: '请填写密码确认',
            PASSWORD_AGAIN_INVALID: '两次输入的密码不一致',
            CAPTCHA_NULL: '请填写验证码',
            CAPTCHA_INVALID: '验证码不正确',
            MOBILE_NULL: '请填写手机号码',
            MOBILE_INVALID: '请输入正确的手机号',
            LOGINNAME_NULL: '请填写用户名',
            LOGINNAME_EXISTS: '用户名已存在',
            LOGINNAME_INVALID: '2-30位字符，可使用字母、数字、下划线且不包含手机号',
            NAME_NULL: '请填写真实姓名',
            NAME_INVALID: '真实姓名错误，应为2-15位中文汉字',
            EMAIL_NULL: '请填写电子邮箱',
            EMAIL_INVALID: '请输入正确的邮箱',
            COMMENT_NULL: '请输入评论'
        }
    }

    console.log('Validation model reference...');

    module.exports = new Validation();

    /**
     * check login name
     *
     * @method checkLoginName
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkLoginName = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'LOGINNAME_NULL');
        if ((/^[^\s]*$/.test(str) == true && /^(?!([1][3|5|8][0-9]{9}))[0-9a-zA-Z_]{2,30}$/.test(str)) == false) {
            return this.combineReturn(false, 'LOGINNAME_INVALID');
        }
        return this.combineReturn(true);
    }

    /**
     * check mobile
     *
     * @method checkMobile
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkMobile = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'MOBILE_NULL');
        if (!('' + str).match(/^[1][3|4|5|7|8][0-9]{9}$/)) return this.combineReturn(false, 'MOBILE_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check email
     *
     * @method checkEmail
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkEmail = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'EMAIL_NULL');
        if (!('' + str).match(/[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+/)) return this.combineReturn(false, 'EMAIL_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check password
     *
     * @method checkPassword
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkPassword = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'PASSWORD_NULL');
        if (str.length < 6) return this.combineReturn(false, 'PASSWORD_LENGTH');
        return this.combineReturn(true);
    }


    /**
     *  a validate route
     *
     * @method validate
     * @param {String} prop LoginName...
     * @param {String} value
     * @returns {Object}
     */
    Validation.prototype.validate = function (prop, value) {
        if (typeof(this["check" + prop]) != "function")
            return this.combineReturn(false);
        return this["check" + prop](value);
    }

    /**
     * combine return result
     *
     * @method combineReturn
     * @param {Boolean} success
     * @param {String} error ErrorMessage Code
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.combineReturn = function (success, error) {
        var obj = {success: success};
        if (error) obj.comment = this.errorMessage[error] || null;
        return obj;
    }
});