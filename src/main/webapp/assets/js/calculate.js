/**
 * Created by XiaoJunfeng on 14/12/31.
 */
;define(function(require, exports, module) {

    function Calculate(){
        this.loan = null;
        this.model = 'simple';
        this.template = '\
        <div class="modal-dialog">\
        <div class="modal-content">\
        <div class="modal-header">\
            <a class="close" data-dismiss="modal">×</a>\
            <h3>收益计算器</h3>\
        </div>\
        <div class="modal-body">\
        <div class="form-horizontal">\
            <div class="form-group">\
                <label class="control-label col-sm-4">投资金额：</label>\
                <div class="col-sm-4">\
                    <input data-val="calc-money" type="text" class="form-control" placeholder="请输入投资金额"/>\
                </div>\
            </div>\
            <div class="form-group">\
                <label class="control-label col-sm-4">投入时长：</label>\
                <div class="col-sm-4">\
                    <input data-val="calc-days" disabled="disabled" type="text" class="form-control" placeholder="请输入投入时长"/>\
                </div>\
            </div>\
            <div class="form-group">\
                <label class="control-label col-sm-4">年化利率：</label>\
                <div class="col-sm-4">\
                    <input data-val="calc-rate" disabled="disabled" type="text" class="form-control" placeholder="请输入年化利率"/>\
                </div>\
            </div>\
            <div class="form-group">\
                <label class="control-label col-sm-4">还款方式：</label>\
                <div class="col-sm-4">\
                    <select data-val="calc-method" disabled="disabled" class="form-control" name="paymentMethod">\
                        <option value="MonthlyInterest">按月付息到期还本</option>\
                        <option value="EqualInstallment">按月等额本息</option>\
                        <option value="EqualPrincipal">按月等额本金</option>\
                        <option value="BulletRepayment">一次性还本付息</option>\
                        <option value="EqualInterest">月平息</option>\
                    </select>\
                </div>\
            </div>\
            <div class="form-group">\
                <div class="col-sm-8 col-sm-offset-4">\
                    <input data-btn="do" type="button" class="btn btn-primary" value="计算" />\
                    <input data-btn="reset" type="button" class="btn btn-warning" value="重置" />\
                </div>\
            </div>\
            <div class="form-group">\
            <div style="width:80%; margin:auto; font-weight:bold;">\
            <div class="col-sm-4">起息日期:今天</div>\
            <div class="col-sm-4">计息天数:<span data-statis="days"></span></div>\
            <div class="col-sm-4">累计收益:<span data-statis="interest"></span></div>\
            </div>\
            </div>\
            <div class="form-group" style="overflow-y: scroll; height:240px!important; display: block;">\
            <table class="table text-center" style="width:80%; margin:auto; border:1px solid #e5e5e5;">\
            <thead>\
                <tr>\
                    <th class="text-center">还款日期</th>\
                    <th class="text-center">收款金额</th>\
                    <th class="text-center">收回本金</th>\
                    <th class="text-center">收回利息</th>\
                    <th class="text-center">剩余本金</th>\
                </tr>\
                </thead>\
                <tbody>\
                </tbody>\
            </table>\
        </div>\
        </div>\
        </div>\
        </div>';
    }

    var Utility = require("utility");
    var utility = new Utility();

    var tool = require("ui-helper");

    Calculate.prototype.init = function(loan, model){
        this.loan = loan;
        if(model) this.model = model;

        this.render();
    }

    Calculate.prototype.render = function(){
        if(!this.loan) return;

        var div = document.createElement('div');
        $(div).addClass('modal').append(this.template).modal('show');

        if(this.loan) {
            //$(div).find('[data-val=calc-money]').val(this.loan.amount);
            $(div).find('[data-val=calc-days]').val(this.loan.lazyDuration);
            $(div).find('[data-val=calc-rate]').val(this.loan.rate/100 + '%');
            $(div).find('[data-val=calc-method]').val(this.loan.method);
        }

        $(div).on('hide.bs.modal', function () {
            $(div).remove();
        });

        this.bindEvents(div);
    }

    Calculate.prototype.bindEvents = function(div){
        var self = this;
        $(div).find('input[data-btn=do]').bind('click',function(e){
            var money = $.trim($(div).find('[data-val=calc-money]').val());

            if(!money || !$.isNumeric(money)){
                $(div).find('[data-val=calc-money]').focus();
                tool.tooltip($(div).find('[data-val=calc-money]'), '请输入数值', null, true);
                return;
            }

            money = parseFloat(money);
            if(money<=0){
                $(div).find('[data-val=calc-money]').focus();
                tool.tooltip($(div).find('[data-val=calc-money]'), '投资金额须大于0', null, true);
                return;
            }

            self.calc.apply(self, [money, div]);
        });
        $(div).find('input[data-btn=reset]').bind('click',function(e){
            $(div).find('[data-val=calc-money]').val('');
            $(div).find('[data-val=calc-money]').focus();
        });
    }

    Calculate.prototype.calc = function(money, div){
        if(!this.loan) return;

        var postData = {};
        postData.method = this.loan.method;
        postData.year = this.loan.years;
        postData.month = this.loan.months;
        postData.day = this.loan.days;
        postData.amount = money;
        postData.rate = this.loan.rate;

        utility.getJSON('calculator', null, postData, function(data){
            var table = $(div).find('table.table').get(0);
            var tbody = table.tBodies[0];
            tbody.innerHTML = '';
            if(data.data){
                var interestCount = 0;
                var daysCount = 0;
                for(var i=0;i<data.data.length; i++){
                    var item = data.data[i];
                    interestCount+= item[3];
                    daysCount += item[5];

                    $(tbody).append('<tr>\
                    <td>'+utility.formatDate(item[0])+' [<span style="color:#ff4500; font-size:12px;font-weight:bold;">'+item[5]+'</span>天]</td>\
                    <td>'+item[1].toFixed(2)+'</td>\
                    <td>'+item[2].toFixed(2)+'</td>\
                    <td>'+item[3].toFixed(2)+'</td>\
                    <td>'+item[4].toFixed(2)+'</td>\
                    </tr>');
                }
                $(div).find('span[data-statis=days]').html(daysCount);
                $(div).find('span[data-statis=interest]').html(interestCount.toFixed(2));
            }
        });

    }

    module.exports = new Calculate();

});