<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>批量生成内容页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
    <style type="text/css">
        .lev1 {
            background-color: #f2f2f2;
        }

        .lev2 {
            text-indent: 4em;
        }

        .check {
            text-align: center;
            width: 40px;
        }
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">批量生成内容页</span>
    </div>
    <div class="layui-card-body">
        <form action="${ base }content/Html/detail" method="post" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">最新发布</label>
                <div class="layui-input-inline">
                    <input type="text" name="count" class="layui-input" autocomplete="false">
                </div>
                <label class="layui-form-label" style="width: 30px; text-align: left">条</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input name="stm" type="text" value="${ param.stm }"
                           autocomplete="off" class="layui-input" id="stm" readonly="readonly"/>
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input name="edm" type="text" value="${ param.edm }"
                           autocomplete="off" class="layui-input" id="edm" readonly="readonly"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容ID段</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input name="fromId" type="text" value="${ param.stm }"
                           autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input name="toId" type="text" value="${ param.edm }"
                           autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <button lay-submit id="okBtn" class="layui-btn">批量生成内容页</button>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">选择栏目</label>
                <div class="layui-input-block">
                    <table class="layui-table" lay-size="sm">
                        <thead>
                        <tr>
                            <th class="check" style="text-align: center">
                                <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                            </th>
                            <th class="check" style="text-align: center">ID</th>
                            <th>栏目名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ categories }" var="category">
                            <c:if test="${ category.type == 1 }">
                                <tr>
                                    <td class="check">
                                        <input type="checkbox" name="categoryId" lay-skin="primary"
                                               value="${ category.id }">
                                    </td>
                                    <td class="check">${ category.id }</td>
                                    <c:if test="${ category.type==1 }">
                                    <td class="lev${ category.level }">
                                        </c:if>
                                        <c:if test="${ category.type!=1 }">
                                    <td>
                                        </c:if>
                                        <c:if test="${ category.level > 1 }">∟ </c:if>
                                            ${ category.name }
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
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
    }).use(['form', 'element', 'laydate', 'layPost'], function () {
        var $ = layui.$, form = layui.form, laydate = layui.laydate,
            layer = layui.layer, layPost = layui.layPost;

        // 渲染日期选择
        laydate.render({elem: '#stm', type: 'date'});
        laydate.render({elem: '#edm', type: 'date'});

        // 全选
        form.on("checkbox(selAll)", function (data) {
            if (data.elem.checked) {
                $("tbody :checkbox").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            } else {
                $("tbody :checkbox").removeAttr('checked');
            }
            form.render("checkbox");
        });

        $("#okBtn").on("click", function () {
            if ($(":checkbox[name='categoryId']:checked").length == 0) {
                layer.msg("请选择栏目!", {icon: 2});
            } else {
                var url = $("form").attr("action");
                var arg = $("form").serialize();
                layPost.post(url, arg, null, null, true);
            }
            return false;
        });
    });
</script>
</body>
</html>