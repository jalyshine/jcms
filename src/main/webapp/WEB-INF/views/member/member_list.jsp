<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员管理</title>
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
        <span class="card-title">会员管理</span>
        <c:import url="../menubar.jsp?module=member" />
    </div>
    <div class="layui-card-body">
        <form action="${ base }member/Member/list" method="get" class="layui-form" id="searchForm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 60px; text-align: left;">注册时间</label>

                    <div class="layui-input-inline" style="width: 100px;">
                        <input name="st" type="text" value="${ param.st }" lay-verify="date"
                               autocomplete="off" class="layui-input" id="stm" readonly="readonly"/>
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input name="et" type="text" value="${ param.et }" lay-verify="date"
                               autocomplete="off" class="layui-input" id="edm" readonly="readonly"/>
                    </div>
                </div>
                <div class="layui-inline" style="width: 100px;">
                    <select name="tts">
                        <option value="">状态</option>
                        <option value="0">正常</option>
                        <option value="1">锁定</option>
                    </select>
                </div>
                <div class="layui-inline" style="width: 100px;">
                    <select name="grp">
                        <option value="">会员组</option>
                        <c:forEach items="${ memberGroups }" var="group">
                            <option value="${ group.id }">${ group.name }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-inline" style="width: 150px;">
                    <input name="kwd" type="text" placeholder="搜索关键词" class="layui-input" value="${ param.kwd }" autocomplete="off"/>
                </div>
                <div class="layui-inline">
                    <input type="hidden" name="ps" value="${ param.ps }">
                    <input type="hidden" name="pn" value="${ param.pn }">
                    <input type="hidden" name="odr" value="reg_time">
                    <input type="submit" class="layui-btn" value="开始搜索"/>
                </div>
            </div>
        </form>
        <div class="layui-btn-group member-oper">
            <button class="layui-btn" data-type="lock">锁定选中会员</button>
            <button class="layui-btn" data-type="unlock">解锁选中会员</button>
            <button class="layui-btn layui-btn-danger" data-type="delete">删除选中会员</button>
        </div>
        <form action="" method="post" id="operForm" class="layui-form">
            <input type="hidden" name="type" value="">
            <table lay-filter="items" lay-even class="layui-table" lay-size="sm">
                <thead>
                <tr>
                    <th style="text-align: center; width:40px;">
                        <input type="checkbox" lay-skin="primary" lay-filter="selAll" id="selAll">
                    </th>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>会员组</th>
                    <th>状态</th>
                    <th>注册时间</th>
                    <th>最后登录时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ page.list }" var="member">
                    <tr>
                        <td style="text-align: center;">
                            <input type="checkbox" name="ids" value="${ member.id }" lay-skin="primary">
                        </td>
                        <td>${ member.id }</td>
                        <td>${ member.userName }</td>
                        <td>${ member.memberGroup.name }</td>
                        <td>${ member.isLock?"<span style='color:red'>锁定</span>":"正常" }</td>
                        <td><fmt:formatDate value="${ member.regTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><fmt:formatDate value="${ member.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <a href="${ base }member/Member/edit?id=${ member.id }"
                               class="layui-btn layui-btn-xs">编辑</a>
                            <a href="${ base }member/Member/delete?id=${ member.id }" name="delete"
                               class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="8" id="pageLine"></td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form', 'laypage', 'laydate', 'element'], function () {
        var layer = layui.layer, form = layui.form, laydate = layui.laydate,
            laypage = layui.laypage, $ = layui.$;

        // 渲染日期选择
        laydate.render({elem: '#stm', type: 'date'});
        laydate.render({elem: '#edm', type: 'date'});

        // 全选
        form.on("checkbox(selAll)", function(data){
            if(data.elem.checked){
                $(":checkbox[name='ids']").prop("checked", true); // 此处不能用attr，否则第二次点击不渲染
            }else{
                $(":checkbox[name='ids']").removeAttr('checked');
            }
            form.render("checkbox");
        })

        $(".member-oper .layui-btn").on("click", function () {
            var action = "${ base }member/Member/batch-" + $(this).data('type');
            layer.confirm('确定要' + $(this).text() + '吗？', {icon: 3, title: '提示'}, function (index) {
                $("#operForm").attr("action", action).submit();
                layer.close(index);
            });
        });

        // 分页
        laypage.render({
            elem: 'pageLine'
            ,count: ${ page.total }
            ,curr: ${ page.pageNum }
            ,limit: 20
            ,layout: ['count', 'prev', 'page', 'next', 'skip']
            ,jump: function(obj, first){
                if(!first){
                    $(":hidden[name='ps']").val(obj.limit);
                    $(":hidden[name='pn']").val(obj.curr);
                    $("#searchForm").submit();
                }
            }
        });

        $("a[name='delete']").click(function () {
            var url = this.href;
            layer.confirm('删除数据不可恢复，是否确定？', {icon: 3, title: '提示'}, function (index) {
                $('<form method="post"></form>').attr("action", url).appendTo($(document.body)).submit();
                layer.close(index);
            });
            return false;
        });
    });
</script>
</body>
</html>