<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>导入内容</title>
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
        <span class="card-title">导入内容</span>
        <c:import url="special_content_menubar.jsp?sid=${ param.sid }"/>
    </div>
    <div class="layui-card-body">
        <form id="searchForm" action="${ base }special/SpecialContent/load" method="get" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline" style="width: 150px;">
                    <select name="mid" lay-filter="model">
                        <option value="">==选择模型==</option>
                        <c:forEach items="${ models }" var="m">
                            <option value="${ m.id }">${ m.name }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-inline" style="width: 150px;">
                    <select name="cid">
                        <option value="">==选择栏目==</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">发布时间</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input name="stm" type="text" lay-verify="date"
                               autocomplete="off" class="layui-input" id="stm" readonly="readonly"/>
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input name="edm" type="text" lay-verify="date"
                               autocomplete="off" class="layui-input" id="edm" readonly="readonly"/>
                    </div>
                </div>
                <div class="layui-inline" style="width: 150px;">
                    <div class="layui-input-inline" style="width: 150px;">
                        <input name="kwd" type="text" placeholder="搜索关键词" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <input type="hidden" name="sid" value="${ param.sid }">
                    <input type="submit" class="layui-btn" value="开始搜索"/>
                </div>
            </div>
        </form>
        <form action="${ base }special/SpecialContent/load" method="post" id="operForm" class="layui-form">
            <input type="hidden" name="sid" value="${ param.sid }">

            <div class="layui-btn-group item-oper layui-inline">
                <button class="layui-btn layui-btn-danger" data-type="load">批量导入内容</button>
            </div>
            <div class="layui-inline" style="width: 150px;">
                <select name="tid">
                    <option value="">==专题分类==</option>
                    <c:forEach items="${ specialTypes }" var="t">
                        <option value="${ t.id }">${ t.name }</option>
                    </c:forEach>
                </select>
            </div>
            <table lay-filter="items" lay-even class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th style="text-align: center; width:40px;">
                        <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                    </th>
                    <th style="width: 50px;">ID</th>
                    <th>标题</th>
                    <th style="width: 150px;">发布时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ contents }" var="content">
                    <tr>
                        <td style="text-align: center;">
                            <input type="checkbox" name="ids" value="${ content.id }" lay-skin="primary">
                            <input type="hidden" name="tableName" value="${ content.table_name }">
                            <input type="hidden" name="title" value="${ content.title }">
                            <input type="hidden" name="description" value="${ content.description }">
                        </td>
                        <td>${ content.id }</td>
                        <td>${ content.title }</td>
                        <td>
                            <fmt:formatDate value="${ content.publish_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
<c:forEach items="${ categoryMap }" var="entry">
    <script type="text/template" id="categoryTemplate_${ entry.key }">
        <option value="">==选择栏目==</option>
            <c:forEach items="${ entry.value }" var="category">
                <option value="${ category.id }">${ category.name }</option>
            </c:forEach>
    </script>
</c:forEach>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form', 'laydate', 'element'], function () {
        var layer = layui.layer, form = layui.form, laydate = layui.laydate, $ = layui.$;

        form.on("select(model)", function (data) {
            var html = "";
            if (data.value != "") {
                if ($("#categoryTemplate_" + data.value).html() != undefined) {
                    html = $("#categoryTemplate_" + data.value).html();
                }
            }
            $("select[name='cid']").html(html);
            form.render("select");
        });

        // 全选
        form.on("checkbox(selAll)", function (data) {
            if (data.elem.checked) {
                $(":checkbox[name='ids']").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            } else {
                $(":checkbox[name='ids']").removeAttr('checked');
            }
            form.render("checkbox");
        });

        // 渲染日期选择
        laydate.render({elem: '#stm', type: 'date'});
        laydate.render({elem: '#edm', type: 'date'});

        $(".item-oper .layui-btn").on("click", function () {
            var type = $(this).data('type');
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $(":checkbox[name='ids']").each(function (i) {
                    if (this.checked != true) {
                        $(this).parent().find(":hidden").remove();
                    }
                });
                $("#operForm").submit();
                layer.close(index);
            });
            return false;
        });
    });
</script>
</body>
</html>