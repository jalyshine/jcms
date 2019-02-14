<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改会员</title>
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
<c:if test="${ member.id == null }">
    <c:set value="${ base }member/Member/add" var="save"/>
</c:if>
<c:if test="${ member.id != null }">
    <c:set value="${ base }member/Member/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑会员</span>
        <c:import url="../menubar.jsp?module=member" />
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="member" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">会员组</label>

                <div class="layui-input-inline">
                    <form:select path="memberGroupId" items="${ memberGroups }" itemLabel="name" itemValue="id"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>

                <div class="layui-input-block">
                    <form:input path="userName" lay-verify="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <c:if test="${ member.id == null }">
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>

                    <div class="layui-input-block">
                        <form:password path="password" lay-verify="psd1" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>

                    <div class="layui-input-block">
                        <input type="password" name="password2" lay-verify="psd2" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </c:if>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>

                <div class="layui-input-block">
                    <form:input path="phone" autocomplete="off" class="layui-input"/>
                    <input type="hidden" value="${ member.id }" name="id">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>

                <div class="layui-input-block">
                    <form:input path="email" autocomplete="off" lay-verify="email" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">积分点数</label>

                <div class="layui-input-block">
                    <form:input path="point" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">VIP设置</label>

                <div class="layui-input-block">
                    <div class="layui-input-inline" style="width: 60px;">
                        <input type="checkbox" lay-filter="isVip" title="VIP">
                    </div>

                    <label class="layui-form-label">等级</label>

                    <div class="layui-input-inline" style="width: 60px;">
                        <form:input path="vipLevel" class="layui-input" readonly="true"/>
                    </div>
                    <label class="layui-form-label">过期时间</label>

                    <div class="layui-input-inline">
                        <form:input path="vipOverTimeStr" class="layui-input"
                                    autocomplete="off" readonly="true"/>
                    </div>
                </div>
            </div>

            <c:if test="${ member.id != null }">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; color: gray;">
                    <legend>详细信息</legend>
                </fieldset>
                <div class="layui-form-item">
                    <label class="layui-form-label">昵称</label>

                    <div class="layui-input-block">
                        <form:input path="memberDetail.nickName" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">生日</label>

                    <div class="layui-input-block">
                        <form:input path="memberDetail.birthdayStr" class="layui-input"
                                    autocomplete="off"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属地区</label>

                    <div class="layui-input-block">
                        <form:input path="memberDetail.area" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">工作单位</label>

                    <div class="layui-input-block">
                        <form:input path="memberDetail.unit" autocomplete="off" class="layui-input"/>
                    </div>
                </div>
            </c:if>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit="" id="submit"
                            <c:if test="${ member.id == null }">
                                class="layui-btn layui-btn-disabled" disabled="disabled"
                            </c:if>
                            <c:if test="${ member.id != null }">
                                class="layui-btn"
                            </c:if>
                    >提交
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="${ base }assets/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['layer', 'form',  'laydate', 'element'], function () {
        var layer = layui.layer, $ = layui.$, form = layui.form, laydate = layui.laydate;

        // 渲染日期选择
        laydate.render({elem: '#vipOverTimeStr', type: 'datetime'});
        laydate.render({elem: '#memberDetail.birthdayStr', type: 'date'});

        form.verify({
            name: function (value) {
                if ($.trim(value).length < 8) {
                    return "用户名不能少于8位！";
                }
            },
            psd1: function (value) {
                if ($.trim(value).length < 8) {
                    return "密码不能少于8位！";
                }
            },
            psd2: function (value) {
                if (value != $("[name='password']").val()) {
                    return "确认密码不一致！";
                }
            }
        });

        $("#userName").change(function () {
            if ($.trim(this.value) == "") {
                $("#submit").attr("class", "layui-btn layui-btn-disabled");
                $("#submit").attr("disabled", true);
            } else {
                var id = $(":hidden[name='id']").val();
                $.post("${ base }member/Member/check", {"name": this.value, "time": new Date()}, function (data) {
                    if (data == "0" || data == id) {
                        $("#submit").attr("class", "layui-btn");
                        $("#submit").removeAttr("disabled");
                    } else {
                        $("#submit").attr("class", "layui-btn layui-btn-disabled");
                        $("#submit").attr("disabled", true);
                        layer.msg('该用户名已经被占用！', {icon: 2});
                    }
                });
            }
        });

        form.on('checkbox(isVip)', function (data) {
            if (this.checked) {
                $("#vipLevel").removeAttr("readonly");
                $("#vipOverTime").removeAttr("readonly");
            } else {
                $("#vipLevel").attr("readonly", "readonly");
                $("#vipOverTime").attr("readonly", "readonly");
            }
        });
    });
</script>
</body>
</html>