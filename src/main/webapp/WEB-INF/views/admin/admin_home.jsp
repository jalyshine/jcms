<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员主页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${ base }assets/css/page_home.css"/>
</head>
<body>
<div style="padding: 15px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>我的信息</span>
                    <a href="${ base }admin/Admin/info" title="修改信息">
                        <i class="layui-icon layui-icon-more"></i>
                    </a>
                </div>
                <div class="layui-card-body" style="height: 180px;">
                    <table class="layui-table" lay-skin="nob">
                        <tr>
                            <td style="width: 70px;">账户信息：</td>
                            <td>${ admin.userName } - <span style="color: #c12">${ admin.adminRole.name }</span></td>
                        </tr>
                        <tr>
                            <td>当前站点：</td>
                            <td>${ siteName }</td>
                        </tr>
                        <tr>
                            <td>起用时间：</td>
                            <td>${ startTime }</td>
                        </tr>
                        <tr>
                            <td>到期时间：</td>
                            <td>${ endTime }</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>快捷方式</span>
                    <a href="${ base }admin/AdminMenu/manage" id="more" title="编辑快捷方式">
                        <i class="layui-icon layui-icon-more"></i>
                    </a>
                </div>
                <div class="layui-card-body" style="height: 180px;">
                    <div class="layui-row layui-col-space15">
                        <c:forEach items="${ quickMenus }" var="qm">
                            <c:if test="${ qm.action != null && qm.action != '' }">
                                <div class="layui-col-md3 quick_menu">
                                    <a data-param="${ qm.data }"
                                       href="${ base }${ qm.module }/${ qm.entity }/${qm.action}?menu=${ qm.id }" >
                                        <p>
                                        <c:if test="${ qm.icon != null }">
                                            <i class="layui-icon">${ qm.icon }</i>
                                        </c:if>
                                        <c:if test="${ qm.icon == null || qm.icon == '' }">
                                            <i class="layui-icon">&#xe653;</i>
                                        </c:if>
                                        </p> <cite>${qm.name}</cite>
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>选择站点</span>
                </div>
                <div class="layui-card-body" style="height: 180px; overflow: auto">
                    <table class="layui-table" lay-size="sm" lay-skin="line">
                        <tbody>
                        <c:forEach items="${ sites }" var="site">
                            <tr>
                                <td>
                                    <a href="${ site.domain }" target="_blank">${ site.name }</a>
                                </td>
                                <td style="width: 60px;">
                                    <c:if test="${ site.id == current_site }">
                                        <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-disabled">进入</a>
                                    </c:if>
                                    <c:if test="${ site.id != current_site }">
                                        <a href="${ base }index?site=${ site.id }"
                                           class="layui-btn layui-btn-xs toggle-site">进入</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md8">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>访问量统计</span>
                </div>
                <div class="layui-card-body" style="height: 385px;">
                    <div id="main" style="width: 100%; height: 100%; "></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>内容统计</span>
                </div>
                <div class="layui-card-body" style="height: 160px;">
                    <div class="layui-row layui-col-space15">
                    <c:forEach items="${ models }" var="model">
                        <div class="layui-col-md6">
                            <div class="grid-demo">
                                <span class="name">${ model.name }</span>
                                <span class="digital">${ model.itemCount }</span>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header card_title">
                    <span>版本信息</span>
                </div>
                <div class="layui-card-body" style="height: 145px;">
                    <table class="layui-table">
                        <tr>
                            <td>当前版本</td>
                            <td>jcms v1.0.1 &nbsp;&nbsp;<a href="javascript:;" style="color: #2ab5a3">在线更新</a></td>
                        </tr>
                        <tr>
                            <td>基于框架</td>
                            <td>ssm + layui v2.4</td>
                        </tr>
                        <tr>
                            <td>数据库</td>
                            <td>MySQL 5.5.53</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/echarts.simple.min.js"></script>
<script>
    layui.config({
        base: '${ base }assets/js/'
    }).use('layPost', function () {
        var $ = layui.jquery, layPost = layui.layPost;

        $(".quick_menu a").on("click", function () {
            var url = this.href;
            var index = url.indexOf(":ajax");
            if(index == -1){
                window.parent.tab.tabAdd({ "title": $(this).find("cite").text(), "href": url });
            } else {
                layPost.post(url.substring(0, index), $(this).attr("data-param"), null, null, true);
            }
            return false;
        });

        $(".toggle-site").on("click", function () {
            window.parent.location = this.href;
            return false;
        });

        var objs = JSON.parse('${ hits }');
        var xLabel = new Array();
        var yLabel = new Array();
        for(var i=0; i<objs.length; i++){
            xLabel.push(objs[i].timeStr);
            yLabel.push(objs[i].hit);
        }

        var myChart = echarts.init(document.getElementById("main"));
        var option = {
            itemStyle: {
                // 设置扇形的颜色
                color: '#009688',
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: xLabel
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: yLabel,
                type: 'line',
                areaStyle: {}
            }]
        };
        myChart.setOption(option);

        $(window).resize(function() {
            myChart.dispose();
            myChart = echarts.init(document.getElementById("main"));
            myChart.setOption(option);
        });
    });
</script>
</body>
</html>