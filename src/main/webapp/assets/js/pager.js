/**
 * Created by XiaoJunfeng on 14/12/9.
 *
 * This is a common library for site pager
 * CSS can be set to container
 */

;define(function(require, exports, module) {
    /**
     * @class AjaxPager
     * @constructor
     */
    function AjaxPager() {

        //just open this function

        /**
         * pagination method
         *
         * @method pagination
         * @param {String|Object} container
         * @param {Integer} pageIndex
         * @param {Integer} total
         * @param {Integer} pageSize If pageSize is null, total mean totalPage, else it mean total records
         * @param {Function} func
         * @param {String} href
         */
        this.pagination = function(container, pageIndex, total, pageSize, func, href){
            var totalPage = total;
            if(pageSize && typeof(pageSize) === "number" && pageSize>0){
                totalPage = Math.floor(total / pageSize);
                if(total%pageSize>0) totalPage++;
            }

            DoAjaxPager(container, pageIndex, totalPage, func, href);
        }

        function DoAjaxPager(container, curPage, totalPage, func, href) {
            var divPager = typeof (container) == "string" ? document.getElementById(container) : container;
            if (!divPager || !divPager.tagName) return;
            divPager.innerHTML = "";
            if (totalPage < 2)
                return;
            var clientId = new Date().getMilliseconds().toString();
            if (divPager.id)
                clientId = divPager.id;
            var buildObj = function BuildIndex() {
                if (curPage > totalPage)
                    curPage = totalPage;
                else if (curPage <= 0)
                    curPage = 1;

                var pagestring = "";

                if (totalPage <= 10) {
                    for (var i = 1; i <= totalPage; i++) {
                        if (i == curPage) {
                            var a = document.createElement("a");
                            divPager.appendChild(a);
                            a.id = "pageactive";
                            a.innerHTML = i;
                        }
                        else {
                            var a = document.createElement("a");
                            divPager.appendChild(a);
                            a.innerHTML = i;
                            a.pager = i;
                            if (href) {
                                a.href = href.replace(/\{0\}/gi, i.toString());
                            }
                            if (func) {
                                a.onclick = function () {                                	
                                    func(this.pager);
                                }
                            }
                        }
                    }
                }
                else {
                    if (1 == curPage) {
                        var a = document.createElement("a");
                        divPager.appendChild(a);
                        a.id = "pageactive";
                        a.innerHTML = "1";
                    }
                    else {
                        var a = document.createElement("a");
                        divPager.appendChild(a);
                        a.innerHTML = "1";
                        a.pager = 1;
                        if (href) {
                            a.href = href.replace(/\{0\}/gi, "1");
                        }
                        if (func) {
                            a.onclick = function () {
                                func(this.pager);
                            }
                        }
                    }

                    if (curPage <= 5) {
                        for (var i = 2; i <= 9; i++) {
                            if (i == curPage) {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.id = "pageactive";
                                a.innerHTML = i;
                            } else {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.innerHTML = i;
                                a.pager = i;
                                if (href) {
                                    a.href = href.replace(/\{0\}/gi, i.toString());
                                }
                                if (func) {
                                    a.onclick = function () {
                                        func(this.pager);
                                    }
                                }
                            }
                        }
                        var _tab = document.createElement("span");
                        _tab.innerHTML = "…";
                        divPager.appendChild(_tab);
                    }
                    else if (curPage < totalPage - 5) {
                        var _tab = document.createElement("span");
                        _tab.innerHTML = "…";
                        divPager.appendChild(_tab);
                        //1-9，再最后一页
                        for (var i = curPage - 3; i <= curPage + 4; i++) {
                            if (i == curPage) {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.id = "pageactive";
                                a.innerHTML = i;
                            } else {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.innerHTML = i;
                                a.pager = i;
                                if (href) {
                                    a.href = href.replace(/\{0\}/gi, i.toString());
                                }
                                if (func) {
                                    a.onclick = function () {
                                        func(this.pager);
                                    }
                                }
                            }
                        }
                        _tab = document.createElement("span");
                        _tab.innerHTML = "…";
                        divPager.appendChild(_tab);
                    }
                    else {
                        var _tab = document.createElement("span");
                        _tab.innerHTML = "…";
                        divPager.appendChild(_tab);
                        //1-9，再最后一页
                        for (var i = totalPage - 8; i <= totalPage - 1; i++) {
                            if (i == curPage) {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.id = "pageactive";
                                a.innerHTML = i;
                            } else {
                                var a = document.createElement("a");
                                divPager.appendChild(a);
                                a.innerHTML = i;
                                a.pager = i;
                                if (href) {
                                    a.href = href.replace(/\{0\}/gi, i.toString());
                                }
                                if (func) {
                                    a.onclick = function () {
                                        func(this.pager);
                                    }
                                }
                            }
                        }
                    }
                    if (totalPage == curPage) {
                        var a = document.createElement("a");
                        divPager.appendChild(a);
                        a.id = "pageactive";
                        a.innerHTML = totalPage;
                    } else {
                        var a = document.createElement("a");
                        divPager.appendChild(a);
                        a.innerHTML = totalPage;
                        a.pager = totalPage;
                        if (href) {
                            a.href = href.replace(/\{0\}/gi, totalPage.toString());
                        }
                        if (func) {
                            a.onclick = function () {
                                func(this.pager);
                            }
                        }
                    }
                }
            }

            buildObj.apply(this, arguments);
            return false;
        }

        function CheckNumber(obj, pageCount) {
            if (obj.value.length == 0)
                return true;
            if (isNaN(obj.value)) {
                alert("Please type number!");
                obj.focus();
                obj.value = "";
                return false;
            }

            var tpage = parseInt(obj.value);
            if (tpage < 1 || tpage > pageCount) {
                alert("Page index must between 1-" + pageCount + "!");
                obj.focus();
                obj.value = "";
                return false;
            }
            return true;
        }

        function ConfirmNumber(objId) {
            var obj = document.getElementById(objId) || objId;
            if (obj.value.length == 0 || obj.value == null) {
                obj.focus();
                obj.value = "";
                return false;
            }
            else if (obj.value.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
                obj.focus();
                obj.value = "";
                return false;
            }
            else if (isNaN(obj.value)) {
                obj.focus();
                obj.value = "";
                return false;
            }
            return true;
        }

        function GetNumber(objId) {
            var obj = document.getElementById(objId) || objId;
            var tpage = parseInt(obj.value);
            return tpage;
        }

    }

    module.exports = new AjaxPager();
});