<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改专题</title>
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
<c:if test="${ special.id == null }">
    <c:set value="${ base }special/Special/add" var="save"/>
</c:if>
<c:if test="${ special.id != null }">
    <c:set value="${ base }special/Special/edit" var="save"/>
</c:if>
<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">专题专题</span>
        <c:import url="../menubar.jsp?module=special"/>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="special" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="siteId"/>

            <div class="layui-form-item">
                <label class="layui-form-label">专题名称</label>

                <div class="layui-input-block">
                    <form:input path="title" lay-verify="title" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">生成目录</label>

                <div class="layui-input-block">
                    <form:input path="dirName" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">缩略图</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty special.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty special.thumb }">
                            src="${ host }/${ special.thumb }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">横幅</label>

                <div class="layui-input-block layui-upload">
                    <form:hidden path="banner"/>
                    <div class="layui-upload-list">
                        <img id="banner-img" style="height: 100px"
                        <c:if test="${ empty special.banner }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty special.banner }">
                            src="${ host }/${ special.banner }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">专题导读</label>

                <div class="layui-input-block">
                    <form:textarea path="description" autocomplete="off" class="layui-textarea"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否禁用</label>

                <div class="layui-input-inline">
                    <form:checkbox path="disabled" title="是"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否推荐</label>

                <div class="layui-input-inline">
                    <form:checkbox path="elite" title="是"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">专题分类</label>

                <div class="layui-input-block">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-normal" id="addType">添加分类</button>
                        <div class="layui-upload-list">
                            <table lay-filter="picTab" lay-size="sm" class="layui-table">
                                <thead>
                                <tr>
                                    <th style="width: 40px;">排序</th>
                                    <th style="width: 120px;">名称</th>
                                    <th style="width: 120px;">路径</th>
                                    <th>描述</th>
                                    <th style="width: 60px;">操作</th>
                                </tr>
                                </thead>
                                <tbody id="itemList">
                                <c:if test="${ specialTypes != null }">
                                    <c:forEach items="${ specialTypes }" var="item">
                                        <tr>
                                            <td><input type="text" name="order" value="${ item.listOrder }"
                                                       class="desc-box" autocomplete="off"></td>
                                            <td><input type="text" name="name" value="${ item.name }" class="desc-box"
                                                       autocomplete="off"></td>
                                            <td><input type="text" name="path" value="${ item.typeDir }"
                                                       class="desc-box" autocomplete="off"></td>
                                            <td><input type="text" name="desc" value="${ item.description }"
                                                       class="desc-box" autocomplete="off"></td>
                                            <td>
                                                <input type="hidden" name="sid" value="${ item.id }">
                                                <button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button>
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
                <label class="layui-form-label">图片报道</label>

                <div class="layui-input-inline">
                    <form:input path="pictureId" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <input type="button" value="选择已有图片" class="layui-btn">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">添加投票</label>

                <div class="layui-input-inline">
                    <form:input path="voteId" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <input type="button" value="选择已有投票" class="layui-btn">
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: gray;">
                <legend>模板设置</legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label">栏目首页</label>

                <div class="layui-input-inline">
                    <form:select path="homeTemplate">
                        <form:option value="">请选择</form:option>
                        <form:options items="${ template_home }" />
                    </form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">栏目列表页</label>

                <div class="layui-input-inline">
                    <form:select path="listTemplate">
                        <form:option value="">请选择</form:option>
                        <form:options items="${ template_list }" />
                    </form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容页</label>

                <div class="layui-input-inline">
                    <form:select path="showTemplate">
                        <form:option value="">请选择</form:option>
                        <form:options items="${ template_show }" />
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
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use(['layPost', 'form', 'element'], function () {
        var $ = layui.$, form = layui.form, layPost = layui.layPost;

        layPost.image('thumb', 'thumb', '${ base }', '${ host }', '${ host_account }', '${ host_password }');
        layPost.image('banner', 'banner', '${ base }', '${ host }', '${ host_account }', '${ host_password }');

        $("button.delete").on("click", function () {
            $(this).parent().parent().remove();
            var tid = $(this).prevAll(":hidden:first").val();
            layPost.post("${ base }content/SpecialType/delete", {id:tid});
            return false;
        });

        $("#addType").on("click", function () {
            var tr = $(['<tr><td><input type="text" name="order" value="0" autocomplete="off" class="desc-box"></td>',
                '<td><input type="text" name="name" value="" autocomplete="off" class="desc-box"></td>',
                '<td><input type="text" name="path" value="" autocomplete="off" class="desc-box"></td>',
                '<td><input type="text" name="desc" value="" autocomplete="off" class="desc-box"></td>',
                '<td><input type="hidden" name="sid" value="0">',
                '<button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td></tr>'].join(''));
            tr.find('.delete').on('click', function () {
                tr.remove();
            });
            $('#itemList').append(tr);
        });

        form.verify({
            title: function (value, item) {
                if ($.trim(value) == "") {
                    return "专题名称不能为空！";
                }
                var flag = false;
                $.ajaxSettings.async = false; // 异步提交
                $.post("${ base }special/Special/check", {'time': new Date(), 'ttl': value}, function (data) {
                    flag = data != 0 && data != $("#id").val();
                });
                $.ajaxSettings.async = true; // 恢复同步提交
                if (flag) {
                    return "该名称已经被占用！";
                }
            }
        });
    });
</script>
</body>
</html>