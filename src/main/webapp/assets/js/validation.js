/**
 * Created by XiaoJunfeng on 14/11/24.
 *
 * Client side validation
 */
;define(function(require, exports, module) {
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
            MOBILE_USED: '手机号码已被使用',
            MOBILE_CAPTCHA_NULL: '请填写手机短信验证码',
            MOBILE_CAPTCHA_INVALID: '验证码无效或已过期，请尝试重新发送',
            MOBILE_CAPTCHA_EXPIRED: '验证码过期，请尝试重新发送',
            AGREEMENT_NULL: '注册需先同意服务条款',
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
            IDNUMBER_INVALID: '请正确填写身份证号码',           
            BANKCARD_NUll:'请输入银行卡号',
            BANKCARD_INVALID:'请输入正确的银行卡号',
            INVEST_NULL:'请输入投资金额',
            INVEST_INVALID:'请输入正确的投资金额',
            INVEST_MIN_AMOUNT_EXCEED:'投资金额不能小于起投金额',
            INVEST_MAX_AMOUNT_EXCEED:'投资金额不能大于最大投资金额',
            INVEST_BALANCE_EXCEED:'投资金额不能大于可用余额',
            INVEST_AVAILABLE_EXCEED:'投资金额不能大于剩余金额',
            COMMENT_NULL:'请输入评论'
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
    Validation.prototype.checkLoginName = function(str){
        if (!str || !str.length) return this.combineReturn(false, 'LOGINNAME_NULL');
        //if(/^[a-zA-Z][0-9a-zA-Z_]{3,15}$/.test(str)==false) return this.combineReturn(false, 'LOGINNAME_INVALID');
        if ((/^[^\s]*$/.test(str) == true && /^(?!([1][3|5|8][0-9]{9}))[0-9a-zA-Z_]{2,30}$/.test(str)) == false){
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
    Validation.prototype.checkMobile = function(str){
        if (!str || !str.length) return this.combineReturn(false, 'MOBILE_NULL');
        if (!(''+str).match(/^[1][3|4|5|7|8][0-9]{9}$/)) return this.combineReturn(false, 'MOBILE_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check email
     *
     * @method checkEmail
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkEmail = function(str){
        if (!str || !str.length) return this.combineReturn(false, 'EMAIL_NULL');
        if (!(''+str).match(/[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+/)) return this.combineReturn(false, 'EMAIL_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check password
     *
     * @method checkPassword
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkPassword = function(str){
        if (!str || !str.length) return this.combineReturn(false, 'PASSWORD_NULL');
        if (str.length<6) return this.combineReturn(false, 'PASSWORD_LENGTH');
        return this.combineReturn(true);
    }

    /**
     * check id number
     *
     * @method checkIdNumber
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkIdNumber = function(str){
    	
        var num = str.toUpperCase();
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {        	
            return this.combineReturn(false, 'IDNUMBER_INVALID');
        }
        //验证前2位，城市符合
        var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
        if(aCity[parseInt(num.substr(0,2))]==null){
            return this.combineReturn(false, 'IDNUMBER_INVALID');
        }

        //下面分别分析出生日期和校验位
        var len, re; len = num.length;
        if (len == 15) {
            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
            var arrSplit = num.match(re);  //检查生日日期是否正确
            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
            var bGoodDay; bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                //alert('身份证号的出生日期不对！');
                return this.combineReturn(false, 'IDNUMBER_INVALID');
            } else {
                //将15位身份证转成18位 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                for(i = 0; i < 17; i ++) {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                num += arrCh[nTemp % 11];

                return this.combineReturn(true);
            }
        }
        if (len == 18) {
            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
            var arrSplit = num.match(re);  //检查生日日期是否正确
            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
            var bGoodDay; bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                //alert('身份证号的出生日期不对！');
                return this.combineReturn(false, 'IDNUMBER_INVALID');
            }
            else { //检验18位身份证的校验码是否正确。 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var valnum;
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                for(i = 0; i < 17; i ++) {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                valnum = arrCh[nTemp % 11];
                if (valnum != num.substr(17, 1)) {
                    //alert('18位身份证号的校验码不正确！');
                    return this.combineReturn(false, 'IDNUMBER_INVALID');
                }

                return this.combineReturn(true);
            }
        }

        return this.combineReturn(false, 'IDNUMBER_INVALID');
    }
    
    /**
     * @author menghuigao
     * check bank card id
     * 
     * @method checkBankCard
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkRealName = function(str){
    	if(str=='')				
    		return this.combineReturn(false, 'NAME_NULL');						
		if(!/[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*/.test(str))		
			return this.combineReturn(false, 'NAME_INVALID');			
		return this.combineReturn(true);
    }
    
    /**
     * @author menghuigao
     * check bank card id
     * 
     * @method checkBankCard
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkBankCard = function(str){
    	if(str=='')				
    		return this.combineReturn(false, 'BANKCARD_NUll');						
		if(!/^[0-9]{15,19}$/.test(str))		
			return this.combineReturn(false, 'BANKCARD_INVALID');			
		return this.combineReturn(true);
    }
    
    /**
     * @author menghuigao
     * Check amount of investment
     * 
     * @method checkAmountInvest
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    /*Validation.prototype.checkAmountInvest = function(str){
    	if(str=='')				
    		return this.combineReturn(false, 'BANKCARD_NUll');						
		if(!/\d/.test(str)||Number(str))		
			return this.combineReturn(false, 'BANKCARD_INVALID');			
		return this.combineReturn(true);
    }*/

    /**
     *  a validate route
     *
     * @method validate
     * @param {String} prop LoginName...
     * @param {String} value
     * @returns {Object}
     */
    Validation.prototype.validate = function(prop, value){
        if(typeof(this["check" + prop])!="function")
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
    Validation.prototype.combineReturn = function(success, error){    	
        var obj = {success:success};
        if(error) obj.comment = this.errorMessage[error] || null;
        return obj;
    }
});