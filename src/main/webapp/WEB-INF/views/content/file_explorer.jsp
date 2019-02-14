<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>资源管理器</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/menu/context.standalone.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/code/jquery.runCode.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/sfont/sfont.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/explorer.css"/>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <table class="header-table">
            <tr>
                <td class="header-table-icon">
                    <a href="javascript:;" id="back"><i class="layui-icon layui-icon-return"></i></a>
                </td>
                <td class="header-table-icon">
                    <a href="javascript:;" id="refresh"><i class="layui-icon layui-icon-refresh"></i></a>
                </td>
                <td>
                    <ul id="path">
                        <li><a href="" data-path=""><i class="layui-icon layui-icon-home"></i></a></li>
                    </ul>
                </td>
                <td class="header-table-icon">
                    <a href="javascript:;" id="upload" title="上传文件" style="float: right">
                        <i class="layui-icon layui-icon-upload-drag"></i>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <div class="layui-card-body">
        <table id="explorer" lay-filter="explorer"></table>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/plugins/menu/context.js"></script>
<script src="${ base }assets/plugins/code/jquery.runCode.js"></script>
<script src="${ base }assets/js/explorer.js"></script>
<script>
    layui.use(['layer', 'element', 'table', 'upload'], function () {
        var layer = layui.layer,
            $ = layui.$,
            table = layui.table,
            upload = layui.upload,
            diskUrl = "${ host }/disk",
            selectFile = {},
            currentPath = "",
            textColor, // 文件列表的字体颜色
            clipboard, // 剪贴板
            isCut;     // true剪切状态 false复制状态

        // 初始化
        var instance = table.render({
            elem: '#explorer'
            , skin: 'nob'
            , size: 'sm'
            , height: 450
            , url: diskUrl
            , where: {
                method: "list"
                , path: currentPath
                , account: '${ host_account }'
                , password: '${ host_password }'
            }
            , method: 'post'
            , cols: [[
                {field: 'icon', width: 35}
                , {field: 'name', title: '文件名', width: 300}
                , {field: 'typeText', title: '类型', width: 100}
                , {field: 'size', title: '大小', width: 100}
            ]]
            , parseData: function (res) {
                var items = loadFile(JSON.parse(res.data));
                return {
                    "code": res.code,
                    "msg": res.msg,
                    "data": items,
                    "count": items.length
                };
            }
        });

        // 进入文件夹
        function direct(path) {
            instance.reload({
                where: {
                    method: "list"
                    , path: path
                    , account: '${ host_account }'
                    , password: '${ host_password }'
                }
            });
            $("#path li:gt(0)").remove();
            if (path.indexOf("/") != -1) {
                var linkPath = "";
                var tokens = path.split("/");
                for (var i = 1; i < tokens.length; i++) {
                    linkPath += "/" + tokens[i];
                    $('<li><a href="javascript:;" data-path="' + linkPath + '">' +
                        tokens[i] + '</a></li>').appendTo($("#path"));
                }
            }
            $("#path a").on("click", function () {
                direct($(this).attr("data-path"));
                return false;
            });
            currentPath = path;
        }

        // 文件操作
        function operate(method, filePath, data, callback) {
            var index;
            $.ajax({
                type: "POST",
                url: diskUrl,
                data: {
                    method: method
                    , path: filePath
                    , data: data
                    , account: '${ host_account }'
                    , password: '${ host_password }'
                },
                dataType: "json",
                beforeSend: function () {
                    index = layer.load(2, {time: 60 * 1000, shade: [0.8, '#393D49']});
                },
                success: function (res) {
                    layer.close(index);
                    switch (res.code) {
                        case -1:
                            layer.msg('操作失败！没有站点权限！', {icon: 2});
                            break;
                        case 1:
                            layer.msg('操作失败！' + res.msg, {icon: 2});
                            break;
                        case 0:
                            callback(res);
                            break;
                    }
                },
                error: function () {
                    layer.msg("操作失败！请检查网络...", {icon: 2});
                    layer.close(index);
                }
            });
        }

        // 右键菜单
        context.init({preventDoubleContext: false}, $);
        context.attach('.layui-table-body', [
            {
                text: '复制', action: function (e) {
                    if (selectFile.path !== undefined) {
                        isCut = false;
                        clipboard = selectFile.path;
                        selectFile.tr.css("color", textColor).siblings().css("color", textColor);
                    }
                }
            },
            {
                text: '剪切', action: function (e) {
                    if(selectFile.path != undefined) {
                        isCut = true;
                        clipboard = selectFile.path;
                        selectFile.tr.css("color", "#ccc").siblings().css("color", textColor);
                    }
                }
            },
            {
                text: '粘贴', action: function (e) {
                    if (clipboard !== undefined && isCut !== undefined) {
                        var method = isCut ? "move" : "copy";
                        operate(method, clipboard, currentPath, function (res) {
                            direct(currentPath);
                        });
                    }
                }
            },
            {divider: true},
            {
                text: '删除', action: function (e) {
                    if (selectFile.path !== undefined) {
                        layer.confirm('确认要删除【' + selectFile.name + '】吗？', function (index) {
                            operate("delete", selectFile.path, "", function (res) {
                                selectFile.tr.remove();
                                layer.close(index);
                            });
                        });
                    }
                }
            },
            {
                text: '新建文件夹', action: function (e) {
                    layer.prompt({title: "新建文件夹"}, function (value, index, elem) {
                        var filePath = currentPath + "/" + value;
                        operate("make", filePath, "", function (res) {
                            layer.close(index);
                            direct(currentPath);
                        });
                    });
                }
            },
            {
                text: '重命名', action: function (e) {
                    if (selectFile.path !== undefined) {
                        var file = selectFile;
                        selectFile = {};
                        layer.prompt({
                                title: "重命名"
                                , value: file.name
                                , cancel: function(index, layero){
                                    selectFile = file;
                                }
                                , btn2: function(index, layero){
                                    selectFile = file;
                                }
                                }
                        , function (value, index, elem) {
                            operate("rename", file.path, value, function (res) {
                                layer.close(index);
                                direct(currentPath);
                            });
                        });
                    }
                }
            },
            {divider: true},
            {
                text: '压缩文件', action: function (e) {
                    if (selectFile.path !== undefined) {
                        operate("zip", selectFile.path, currentPath, function (res) {
                            direct(currentPath);
                        });
                    }
                }
            },
            {
                text: '解压到当前文件夹', action: function (e) {
                    if (selectFile.path !== undefined && selectFile.type == "zip") {
                        operate("unzip", selectFile.path, currentPath, function (res) {
                            direct(currentPath);
                        });
                    }
                }
            },
            {divider: true},
            {
                text: '上传文件', action: function (e) {
                    $("#upload").click();
                }
            },
            {
                text: '下载', action: function (e) {
                    if (selectFile.path !== undefined && selectFile.type !== "folder") {
                        var form = $("<form></form>").attr("action", diskUrl).attr("method", "post");
                        $('<input type="hidden" name="path" />').val(selectFile.path).appendTo(form);
                        $('<input type="hidden" name="method" />').val("download").appendTo(form);
                        $('<input type="hidden" name="account" />').val("${ host_account }").appendTo(form);
                        $('<input type="hidden" name="password" />').val("${ host_password }").appendTo(form);
                        form.appendTo('body').submit().remove();
                    }
                }
            }
        ]);

        // 双击操作
        table.on('rowDouble(explorer)', function (obj) {
            var fileName = obj.data.name;
            var filePath = currentPath + "/" + fileName;
            switch (obj.data.typeName) {
                case "folder":
                    direct(filePath);
                    break;
                case "file":
                    layer.alert("未知的文件类型");
                    break;
                case "text":
                    operate("read", filePath, "", function (res) {
                        var file = selectFile;
                        selectFile = {};
                        var codeClass = "html";
                        if(fileName.indexOf(".css") != -1){
                            codeClass = "css";
                        } else if (fileName.indexOf(".xml") != -1){
                            codeClass = "xml";
                        } else if (fileName.indexOf(".js") != -1){
                            codeClass = "js";
                        }
                        layer.open({
                            title: fileName
                            ,area: ['100%', '100%']
                            ,content: '<pre style="width: 100%; height: 100%; padding: 5px; overflow-y: auto; background-color: #272822;">' +
                                '<code id="codeContent" contenteditable name="editCode" class="' + codeClass + '"></code></pre>'
                            , success: function(layero, index){
                                $("#codeContent").text(res.data);
                                if (typeof DlHighlight !== 'undefined') {
                                    DlHighlight.HELPERS.highlightByName("editCode", "code");
                                }
                            }
                            , btn: ['保存提交', '取消']
                            , yes: function (index, layero) {
                                var value = layero.find("#codeContent").text();
                                operate("save", filePath, value, null);
                                layer.close(index);
                            }
                            , cancel: function(index, layero){
                                selectFile = file;
                            }
                            , btn2: function (index, layero) {
                                selectFile = file;
                            }
                        });
                    });
                    break;
                case "image":
                    var url = '${ host }/' + filePath;
                    var style = 'style="height: 100%;"';
                    layer.open({
                        type: 1
                        , title: fileName
                        , maxWidth: 500
                        , content: '<div style="width: 500px; height: 320px; overflow: hidden; text-align: center">' +
                            '<img src="' + url + '" alt="" ' + style + '/></div>'
                    });
                    break;
                case "audio":
                    layer.alert("音频文件");
                    break;
                case "video":
                    layer.alert("视频文件");
                    break;
                case "zip":
                    layer.alert("zip压缩文件");
                    break;
                default:
                    break;
            }
        });

        // 单击操作
        table.on('row(explorer)', function (obj) {
            if(textColor === undefined){
                textColor = obj.tr.css("color");
            }
            selectFile.tr = obj.tr;
            selectFile.name = obj.data.name;
            selectFile.path = currentPath + "/" + obj.data.name;
            selectFile.type = obj.data.typeName;
            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });

        // 返回上一页
        $("#back").on("click", function () {
            var index = currentPath.lastIndexOf("/");
            if (index != -1) {
                var path = currentPath.substring(0, index);
                direct(path);
            }
        });

        // 刷新页面
        $("#refresh").on("click", function () {
            direct(currentPath);
        });

        // 上传操作
        upload.render({
            elem: '#upload'
            , url: diskUrl
            , accept: 'file'
            , data: {
                method: "upload"
                , account: '${ host_account }'
                , password: '${ host_password }'
            }
            , before: function (obj) {
                this.data.path = currentPath;
            }
            , done: function (res) {
                direct(currentPath);
            }
        });

        // 键盘操作，删除、复制、剪切、粘贴
        $("body").on("keypress", function (e) {
            if (e.keyCode === 46 && selectFile.tr !== undefined) {
                layer.confirm('确认要删除【' + selectFile.name + '】吗？', function (index) {
                    operate("delete", selectFile.path, "", function (res) {
                        selectFile.tr.remove();
                        layer.close(index);
                    });
                });
            }
            if (e.ctrlKey) {
                switch (e.which) {
                    case 99:    // 复制
                        if(selectFile.path != undefined){
                            isCut = false;
                            clipboard = selectFile.path;
                            selectFile.tr.css("color", textColor).siblings().css("color", textColor);
                        }
                        break;
                    case 120:   // 剪切
                        if(selectFile.path != undefined) {
                            isCut = true;
                            clipboard = selectFile.path;
                            selectFile.tr.css("color", "#ccc").siblings().css("color", textColor);
                        }
                        break;
                    case 118:   // 粘贴
                        if (clipboard !== undefined && isCut !== undefined) {
                            var method = isCut ? "move" : "copy";
                            operate(method, clipboard, currentPath, function (res) {
                                direct(currentPath);
                            });
                        }
                        break;
                }
            }
        });
    });
</script>
</body>
</html>