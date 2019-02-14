layui.define(['layer', 'upload'], function (exports) {
    "use strict";
    var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer : parent.layer
        ,upload = parent.upload === undefined ? layui.upload : parent.upload;

    var layPost = {
        post: function (url, params) {
            var success = arguments[2] ? arguments[2] : null;   // code = 0回调方法
            var failed = arguments[3] ? arguments[3] : null;    // code = 1回调方法
            var loading = arguments[4] ? arguments[4] : false;  // 耗时操作，使用load弹出层
            var loadIndex;
            $.ajax({
                type: "POST"
                , url: url
                , data: params
                , dataType: "json"
                , cache: false
                , beforeSend: function () {
                    if (loading) {
                        loadIndex = layer.load(2, {time: 60 * 1000, shade: [0.8, '#393D49']});
                    }
                }
                , success: function (res) {
                    if (loading) {
                        layer.close(loadIndex);
                    }
                    switch (res.code) {
                        case -1:
                            layer.msg('操作失败！没有站点权限！', {icon: 2, shade: [0.8, '#393D49']});
                            break;
                        case 1:
                            var msg = "";
                            if (failed != null) {
                                msg = failed(res.data);
                            }
                            if (msg == undefined || msg == "") {
                                msg = res.msg == ""?"操作失败！":res.msg;
                            }
                            layer.msg(msg, {icon: 2, shade: [0.8, '#393D49']});
                            break;
                        case 0:
                            msg = "";
                            if (success != null) {
                                msg = success(res.data);
                            }
                            if(msg != undefined){
                                if(msg == ""){
                                    msg = res.msg == ""?"操作成功！":res.msg;
                                }
                                layer.msg(msg, {icon: 1, shade: [0.8, '#393D49']});
                            }
                            break;
                    }
                }
                , error: function () {
                    if (loading) {
                        layer.close(loadIndex);
                    }
                    layer.msg("操作失败！请检查网络...", {icon: 2});
                }
            });
        }
        , image: function (name, type, base, host, account, password) {
            var hid = '#' + name;
            var btn = hid + "-img";
            var upInst = upload.render({
                elem: btn
                , url: host + '/upload?type=' + type
                , data: {account: account, password: password}
                , before: function (obj) {
                    obj.preview(function (index, file, result) {
                        $(btn).attr('src', result);
                    });
                }, done: function (res) {
                    switch (res.code) {
                        case -1:
                            layer.msg('上传失败！没有站点权限！', {icon: 2});
                            break;
                        case 1:
                            layer.msg('上传失败！' + res.msg, {icon: 2});
                            break;
                        case 0:
                            if (res.siteId == undefined) {
                                var url = base + 'upload/attach';
                                var arg = {'data': JSON.stringify(res)};
                                $.post(url, arg, null);
                            }
                            $(hid).val(res.data.src);
                            layer.msg('上传成功！', {icon: 1});
                            break;
                    }
                }, error: function () {
                    layer.alert("上传失败，请检查网络！");
                }
            });
            return upInst;
        }
    }
    exports('layPost', layPost);
});