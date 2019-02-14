layui.define(function (exports) {
    exports("url", function (href, pName, pValue) {
        var idx1 = href.indexOf("?");
        var idx2 = href.indexOf("#");
        idx2 = idx2 == -1 ? href.length : idx2;
        if (idx1 == -1) idx1 = idx2;
        var path = href.substring(0, idx1);
        var search = href.substring(idx1, idx2);
        var hash = href.substring(idx2);

        if (typeof (pValue) == "undefined") {     // 获取参数值
            var paraString = href.substring(href.indexOf("?") + 1).split("&");
            var paraObj = {}, i, j;
            for (i = 0; j = paraString[i]; i++) {
                paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
            }
            var returnValue = paraObj[pName.toLowerCase()];
            if (typeof (returnValue) == "undefined") return "";
            else return returnValue;
        } else if (pValue == null) {            // 删除参数，并返回处理后的href
            if (idx1 != -1) {
                var idx = search.indexOf(pName + "=");
                if (idx != -1) {
                    var sdx = search.indexOf("&");
                    if (sdx == -1) {
                        search = "";
                    } else if (sdx > idx) {
                        search = "?" + search.substring(sdx + 1);
                    } else {
                        var tmp = search.substring(idx);
                        sdx = tmp.indexOf("&");
                        search = search.substring(0, idx - 1);
                        if (sdx != -1) {
                            search += tmp.substring(sdx);
                        }
                    }
                }
            }
            return path + search + hash;
        } else if (typeof pValue == 'string') { // 设置参数值，并返回处理后的href
            if (idx1 == idx2) {//无页面参数
                search = "?" + pName + "=" + pValue;
            } else {
                var idx = search.indexOf(pName + "=");
                if (idx == -1) {              //有页面参数，但不包含目标参数
                    search += "&" + pName + "=" + pValue;
                } else {
                    var tmp = search.substring(idx);
                    var sdx = tmp.indexOf("&");
                    if (sdx == -1) {         //有页面参数，且包含目标参数，目标参数在末尾
                        search = search.substring(0, idx) + pName + "=" + pValue;
                    } else {                //有页面参数，且包含目标参数，目标参数在中间
                        search = search.substring(0, idx) + pName + "=" + pValue + tmp.substring(sdx, tmp.length);
                    }
                }
            }
            return path + search + hash;
        }
    });
});
