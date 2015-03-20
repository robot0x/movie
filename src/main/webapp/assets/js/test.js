/**
 * Created by XiaoJunfeng on 14/11/10.
 */

;define(function(require, exports, module) {

    var Utility = require("utility.js");

    var utility = new Utility();

    utility.getJSON("/test/test.json", {header1:"xjfhnsd"}, {col1:1, col2:2}, function(data){
        alert(data);
    })

});
