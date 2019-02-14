<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/编辑会员组</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
</head>
<body>
<c:if test="${ memberGroup.id == null }">
    <c:set value="${ base }member/MemberGroup/add" var="save"/>
</c:if>
<c:if test="${ memberGroup.id != null }">
    <c:set value="${ base }member/MemberGroup/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑会员组</span>
        <c:import url="../menubar.jsp?module=member"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="memberGroup" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">会员组名</label>

                <div class="layui-input-inline">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
                <form:checkbox path="isCore" title="系统添加"/>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员名颜色</label>
                <div class="layui-input-inline">
                    <div class="layui-input-inline" style="width: 120px;">
                        <form:input path="userNameColor" autocomplete="off"
                                    class="layui-input" placeholder="请选择颜色" readonly="true"/>
                    </div>
                    <div class="layui-inline" style="left: -11px;">
                        <div id="color-form"></div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员组图标</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="icon"/>
                    <div class="layui-upload-list">
                        <img id="icon-img" style="height: 100px"
                        <c:if test="${ empty memberGroup.icon}">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty memberGroup.icon }">
                            src="${ host }/${ memberGroup.icon }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户组描述</label>

                <div class="layui-input-block">
                    <form:textarea path="description" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户权限</label>

                <div class="layui-input-block">
                    <form:checkbox path="allowPost" title="允许投稿"/>
                    <form:checkbox path="allowPostVerify" title="投稿免审"/>
                    <form:checkbox path="allowUpgrade" title="自助升级"/>
                    <form:checkbox path="allowSendMessage" title="发短消息"/>
                    <form:checkbox path="allowUpload" title="上传附件"/>
                    <form:checkbox path="allowSearch" title="搜索权限"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">升级价格</label>

                <div class="layui-inline">
                    <label class="layui-form-label">包年（元）</label>

                    <div class="layui-input-inline" style="width: 100px;">
                        <form:input path="priceYear" lay-verify="number" autocomplete="off" class="layui-input"/>
                    </div>
                    <label class="layui-form-label">包月（元）</label>

                    <div class="layui-input-inline" style="width: 100px;">
                        <form:input path="priceMonth" lay-verify="number" autocomplete="off" class="layui-input"/>
                    </div>
                    <label class="layui-form-label">包日（元）</label>

                    <div class="layui-input-inline" style="width: 100px;">
                        <form:input path="priceDay" lay-verify="number" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">积分小于</label>

                <div class="layui-input-inline">
                    <form:input path="maxPoint" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">星星数</label>

                <div class="layui-input-inline">
                    <form:input path="starNum" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">最大消息数</label>

                <div class="layui-input-inline">
                    <form:input path="allowMessageNum" autocomplete="off" lay-verify="number" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日投稿数</label>

                <div class="layui-input-inline">
                    <form:input path="allowPostNum" autocomplete="off" lay-verify="number" class="layui-input"/>
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
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/fixed.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'colorpicker', 'layPost', 'element'], function () {
        var $ = layui.$, layPost = layui.layPost, colorpicker = layui.colorpicker;

        //表单赋值
        colorpicker.render({
            elem: '#color-form'
            ,color: '#000'
            ,done: function(color){
                $('#userNameColor').val(color);
            }
        });

        layPost.image('icon', 'icon', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        $(":text[name='name']").change(function () {
            if ($.trim(this.value) == "") {
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $(":hidden[name='id']").val();
                $.post("${ base }member/MemberGroup/check",
                    {"name": this.value, "time": new Date()}, function (data) {
                        if (data == "0" || data == id) {
                            $("#submit").attr("class", "layui-btn");
                            $("#submit").removeAttr("disabled");
                        } else {
                            $("#submit").attr("class", "layui-btn layui-btn-disabled");
                            $("#submit").attr("disabled", true);
                            layer.msg('该组名已经被占用！', {icon: 2});
                        }
                    });
            }
        });
    });
</script>
</body>
</html>