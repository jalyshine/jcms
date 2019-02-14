<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>本站信息</title>
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

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">本站信息</span>
    </div>
    <div class="layui-card-body">
        <form:form action="${ base }admin/SiteInfo/manage" method="post" modelAttribute="siteInfo" cssClass="layui-form">

            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>

                <div class="layui-input-block">
                    <form:input path="name" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>

                <div class="layui-input-inline">
                    <form:input path="address" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">邮编</label>

                <div class="layui-input-inline">
                    <form:input path="postCode" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>

                <div class="layui-input-inline">
                    <form:input path="telephone" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">传真</label>

                <div class="layui-input-inline">
                    <form:input path="fax" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系人</label>

                <div class="layui-input-inline">
                    <form:input path="manager" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">手机号</label>

                <div class="layui-input-inline">
                    <form:input path="phone" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">网址</label>

                <div class="layui-input-inline">
                    <form:input path="url" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">E-mail</label>

                <div class="layui-input-inline">
                    <form:input path="email" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">微信</label>

                <div class="layui-input-inline">
                    <form:input path="weChat" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">qq</label>

                <div class="layui-input-inline">
                    <form:input path="qq" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备案号</label>

                <div class="layui-input-inline">
                    <form:input path="icp" autocomplete="off" class="layui-input"/>
                </div>
                <label class="layui-form-label">地理坐标</label>

                <div class="layui-input-inline">
                    <form:input path="position" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">格式：110.4132852119,21.2008754681</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">logo</label>

                <div class="layui-input-inline">
                    <form:hidden path="logo"/>
                    <div class="layui-upload-list">
                        <img id="logo-img" style="height: 100px"
                        <c:if test="${ empty siteInfo.logo }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty siteInfo.logo }">
                            src="${ host }/${ siteInfo.logo }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">二维码</label>

                <div class="layui-input-inline">
                    <form:hidden path="qrCode"/>
                    <div class="layui-upload-list">
                        <img id="qrCode-img" style="height: 100px"
                        <c:if test="${ empty siteInfo.qrCode }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty siteInfo.qrCode }">
                            src="${ host }/${ siteInfo.qrCode }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">其他图片</label>

                <div class="layui-input-inline">
                    <form:hidden path="image"/>
                    <div class="layui-upload-list">
                        <img id="image-img" style="height: 100px"
                        <c:if test="${ empty siteInfo.image }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty siteInfo.image }">
                            src="${ host }/${ siteInfo.image }">
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">版权信息</label>

                <div class="layui-input-block">
                    <form:textarea path="copyRight" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>

            <jfp:SqlDataSource id="DBx"
                               cmd="SELECT * FROM json_attribute
                                            WHERE site_id = ${ siteInfo.siteId }
                                            AND table_name = 'site_info'
                                            AND field_name = 'other'" />
            <jfp:JsonDataSource id="DBy" value="${ siteInfo.other }" />
            <form:hidden path="other" />
            <jfp:Repeater dataSourceID="DBx" var="item">
                <div class="layui-form-item" id="plusArea">
                    <label class="layui-form-label">${ item.name }</label>

                    <div class="layui-input-block">
                        <c:if test="${ item.type == '单行' }">
                            <input type="text" name="${ item.name }" value="${ DBy[item.name] }" class="layui-input">
                        </c:if>
                        <c:if test="${ item.type == '段落' }">
                            <textarea name="${ item.name }" class="layui-textarea">${ DBy[item.name] }</textarea>
                        </c:if>
                    </div>
                </div>
            </jfp:Repeater>

            <div class="layui-form-item">
                <label class="layui-form-label form-title"> </label>

                <div class="layui-input-block">
                    <button lay-submit="" id="submit" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>

        </form:form>
        <form action="${ base }admin/JsonAttribute/save" method="post" class="layui-form" id="fm">
            <input type="hidden" name="tableName" value="site_info">
            <input type="hidden" name="fieldName" value="other">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>附加字段</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label">字段管理</label>

                <div class="layui-input-block">
                    <a href="javascript:;" class="layui-btn" id="addItem">
                        <i class="layui-icon layui-icon-add-1"></i> 添加
                    </a>
                    <a href="javascript:;" class="layui-btn layui-btn-primary" id="saveItem">提交</a>

                    <table class="layui-table" lay-size="sm" lay-skin="line" id="fieldTable">
                        <thead>
                        <tr>
                            <th style="width: 200px">名称</th>
                            <th style="width: 150px;">类别</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jfp:Repeater dataSourceID="DBx" var="item">
                            <tr>
                                <td>${ item.name }</td>
                                <td>${ item.type }</td>
                                <td>
                                    <input type="hidden" value="${ item.id }">
                                    <button class="layui-btn layui-btn-danger delete">删除</button>
                                </td>
                            </tr>
                        </jfp:Repeater>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>



<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'layPost', 'layer'], function () {
        var $ = layui.$, layPost = layui.layPost, layer = layui.layer, form = layui.form;

        layPost.image('logo', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('qrCode', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('image', 'customer', '${ base }', '${ host }', '${ host_account }', '${ host_password }');

        $("#submit").on("click", function () {
            var url = $("form").attr("action");
            var arg = $("form").serialize();
            layPost.post(url, arg);
            return false;
        });

        $("#show").on("click", function () {
            layer.open({
                type: 1,
                anim: 6,
                content: $('#plus-temp').html(),
                area: ['500px', '300px'],
                shade: 0.6,
                success: function (layero, lockIndex) {

                }
            });
        });

        $("#addItem").on("click", function () {
            var tr = $(['<tr><td><input type="text" name="names" class="layui-input"></td>'
                    , '<td><select name="types"><option value="单行">单行</option>'
                    , '<option value="时间">时间</option><option value="段落">段落</option></select></td>'
                    , '<td><button class="layui-btn layui-btn-danger delete">删除</button></td>'
                    , '</tr>'].join(''));
            $("#fieldTable tbody").append(tr);
            form.render('select');
        });

        $("#saveItem").on("click", function () {
            $.ajax({
                type: "POST"
                , url: $("#fm").attr("action")
                , data: $("#fm").serialize()
                , cache: false
                , success: function (res) {
                     alert("success")
                }
            });
        });

        var other = {};

        $("#plusArea input").on("change", function () {
            var argName = $(this).attr("name");
            var argValue = $(this).val();
            other[argName] = argValue;
            var str = JSON.stringify(other);
            alert(str);
            $("#other").val(str);
        });

        $("#plusArea textarea").on("change", function () {
            var argName = $(this).attr("name");
            var argValue = $(this).val();
            other[argName] = argValue;
            var str = JSON.stringify(other);
            alert(str);
            $("#other").val(str);
        });
    });
</script>
</body>
</html>