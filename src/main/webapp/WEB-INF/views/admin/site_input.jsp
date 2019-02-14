<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改站点</title>
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
<c:if test="${ site.id == null }">
    <c:set value="${ base }admin/Site/add" var="save"/>
</c:if>
<c:if test="${ site.id != null }">
    <c:set value="${ base }admin/Site/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑站点</span>
        <c:import url="../menubar.jsp?module=admin"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="site" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>

            <div class="layui-form-item">
                <label class="layui-form-label">站点名</label>

                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">站点目录</label>

                <div class="layui-input-block">
                    <c:if test="${ site.id != 1 }">
                        <form:input path="dirName" autocomplete="off" class="layui-input"/>
                    </c:if>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">站点域名</label>

                <div class="layui-input-block">
                    <form:input path="domain" autocomplete="off" class="layui-input" placeholder="http://"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">站点标题</label>

                <div class="layui-input-block">
                    <form:input path="title" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">关键词</label>

                <div class="layui-input-block">
                    <form:input path="keywords" autocomplete="off" class="layui-input" placeholder="请用半角英文逗号,分隔"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">站点描述</label>

                <div class="layui-input-block">
                    <form:textarea path="description" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>账户设置</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>

                <div class="layui-input-block">
                    <form:input path="userName" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>

                <div class="layui-input-block">
                    <form:input path="password" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">服务期限</label>

                <div class="layui-input-inline">
                    <form:input path="startTimeStr" autocomplete="off" class="layui-input" lay-verify="datetime"/>
                </div>
                <div class="layui-input-inline" style="text-align: center; width: 40px; line-height: 35px;">
                    至
                </div>
                <div class="layui-input-inline">
                    <form:input path="endTimeStr" autocomplete="off" class="layui-input" lay-verify="datetime"/>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>模板设置</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label">可用模板</label>

                <div class="layui-input-block">
                    <form:checkboxes lay-filter="use" path="templates" items="${ templates }" itemLabel="name"
                                     itemValue="name"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">默认样式</label>

                <div class="layui-input-inline">
                    <form:select path="uiStyle">
                        <form:option value="">请选择</form:option>
                        <form:option value="${ site.uiStyle }">${ site.uiStyle }</form:option>
                    </form:select>
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
    }).use(['form', 'element', 'laydate'], function () {
        var $ = layui.$, form = layui.form, laydate = layui.laydate;

        laydate.render({elem: '#startTimeStr', type: 'datetime'});
        laydate.render({elem: '#endTimeStr', type: 'datetime'});

        $("#name").change(function () {
            if ($.trim(this.value) == "") {
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $(":hidden[name='id']").val();
                $.post("${ base }admin/Site/check", {"name": this.value, "time": new Date()}, function (data) {
                    if (data == "0" || data == id) {
                        $("#submit").attr("class", "layui-btn");
                        $("#submit").removeAttr("disabled");
                    } else {
                        $("#submit").attr("class", "layui-btn layui-btn-disabled");
                        $("#submit").attr("disabled", true);
                        layer.msg('该站点名已经被占用！', {icon: 2});
                    }
                });
            }
        });

        form.on("checkbox(use)", function (data) {
            if (data.elem.checked) {
                $('<option></option>').val(data.elem.value).text(data.elem.value).appendTo($("#uiStyle"));
            } else {
                $("#uiStyle option[value='" + data.elem.value + "']").remove();
            }
            form.render("select");
        });

        var templates = '${ site.templates }'.split(",");
        $(":checkbox[name='templates']").each(function () {
            for (var i = 0; i < templates.length; i++) {
                if (templates[i] == this.value) {
                    this.checked = "checked";
                    break;
                }
            }
        });
        form.render("checkbox");
    });
</script>

</body>
</html>