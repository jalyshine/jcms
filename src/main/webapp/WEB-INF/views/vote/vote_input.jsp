<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加投票</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .desc-box {
            width: 100%;
            border: 0;
            color: gray;
            font-size: 10pt;
            background-color: transparent;
        }
    </style>
</head>
<body>
<c:if test="${ vote.id == null }">
    <c:set value="${ base }vote/Vote/add" var="save"/>
</c:if>
<c:if test="${ vote.id != null }">
    <c:set value="${ base }vote/Vote/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑投票</span>
        <c:import url="../menubar.jsp?module=vote"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="vote" cssClass="layui-form">

            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>

                <div class="layui-input-block">
                    <form:input path="title" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否多选</label>

                <div class="layui-input-block">
                    <form:checkbox path="isMultiple" title="多选"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">投票选项</label>

                <div class="layui-input-block">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-normal" id="addItem">选择图片</button>
                        <button type="button" class="layui-btn" id="uploadItem">开始上传</button>
                        <button type="button" class="layui-btn" id="addOption">添加选项</button>
                        <div class="layui-upload-list">
                            <table lay-filter="picTab" lay-size="sm" class="layui-table">
                                <thead>
                                <tr>
                                    <th>图片</th>
                                    <th>文字</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="itemList">
                                <c:if test="${ vote.id != null }">
                                    <c:forEach items="${ vote.voteOptions }" var="item">
                                        <tr>
                                            <td>
                                                <c:if test="${ item.icon != null && item.icon != '' }">
                                                    <img src="${ host }/${ item.icon }"/>
                                                </c:if>
                                                <input type="hidden" name="optIco" value="${ item.icon }">
                                            </td>
                                            <td><input type="text" name="optTxt" value="${ item.text }"
                                                       class="desc-box"></td>
                                            <td>已存储</td>
                                            <td>
                                                <button class="layui-btn layui-btn-xs layui-btn-danger delete">删除
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">上线时间</label>

                <div class="layui-input-inline">
                    <form:input path="fromTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">下线时间</label>

                <div class="layui-input-inline">
                    <form:input path="toTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">投票介绍</label>

                <div class="layui-input-block">
                    <form:textarea path="description" cssClass="layui-textarea"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">查看结果</label>

                <div class="layui-input-block">
                    <form:checkbox path="allowView" title="允许"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">游客投票</label>

                <div class="layui-input-block">
                    <form:checkbox path="allowGuest" title="允许"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">奖励积分</label>

                <div class="layui-input-inline">
                    <form:input path="creditPoint" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">投票间隔</label>

                <div class="layui-input-inline">
                    <form:input path="intervalDays" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">N天后可再次投票，0表示此IP地址只能投票一次</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否禁用</label>

                <div class="layui-input-block">
                    <form:checkbox path="disabled" title="禁用"/>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'laydate', 'upload', 'element'], function () {
        var form = layui.form, laydate = layui.laydate, upload = layui.upload, $ = layui.$;

        laydate.render({elem: '#fromTimeStr', type: 'datetime'});
        laydate.render({elem: '#toTimeStr', type: 'datetime'});

        var itemListView = $('#itemList')
            , uploadListIns = upload.render({
            elem: '#addItem'
            , url: '${ host }/upload?type=icon'
            , data: {account: '${ host_account }', password: '${ host_password }'}
            , accept: 'file'
            , multiple: true
            , auto: false
            , bindAction: '#uploadItem'
            , choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td><img src=""/><input type="hidden" name="optIco"></td>'
                        , '<td><input type="text" name="optTxt" value="' + file.name + '" class="desc-box" /></td>'
                        , '<td>等待上传</td>'
                        , '<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button>'
                        , '<button class="layui-btn layui-btn-xs reload layui-hide">重传</button></td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        //清空 input explorer 值，以免删除后出现同名文件不可选
                        uploadListIns.config.elem.next()[0].value = '';
                    });

                    itemListView.append(tr);
                });
            }
            , done: function (res, index, upload) {
                switch (res.code) {
                    case -1:
                        layer.msg('上传失败！没有站点权限！', {icon: 2});
                        break;
                    case 1:
                        layer.msg('上传失败！' + res.msg, {icon: 2});
                        break;
                    case 0:
                        if (res.siteId == undefined) {
                            var url = '${ base }upload/attach';
                            var arg = {'data': JSON.stringify(res)};
                            $.post(url, arg, null);
                        }
                        var tr = itemListView.find('tr#upload-' + index)
                            , tds = tr.children();
                        tds.eq(0).find(":hidden").val(res.data.src);
                        tds.eq(0).find("img").attr("src", "${ host }/" + res.data.src);
                        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                        tds.eq(3).html(''); //清空操作
                        return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            , error: function (index, upload) {
                var tr = itemListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.reload').removeClass('layui-hide'); //显示重传
            }
        });

        $("button.delete").on("click", function () {
            $(this).parent().parent().remove();
            return false;
        });

        $("#addOption").click(function () {
            var tr = $(['<tr><td><input type="hidden" name="optIco"></td>'
                , '<td><input type="text" name="optTxt" class="desc-box" /></td>'
                , '<td>-</td>'
                , '<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td></tr>']
                .join(''));
            //删除
            tr.find('.delete').on('click', function () {
                tr.remove();
            });
            $('#itemList').append(tr);
        });

    });
</script>
</body>
</html>