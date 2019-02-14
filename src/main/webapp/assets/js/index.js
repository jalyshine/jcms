var tab;
var site = 1;
var sideWidth;
var admin_side_spread = true;
var baseUrl = document.getElementById("baseUrl").value;

layui.config({
    base: baseUrl + 'assets/js/'
}).use(['element', 'layer', 'tab', 'form', 'layPost'], function () {
    var element = layui.element, $ = layui.jquery, layer = layui.layer, layPost = layui.layPost;

    sideWidth = $('.layui-side').width();
    tab = layui.tab({elem: '.admin-nav-card'});
    tab.set({
        maxSetting:20
    });

    $('#admin-side-toggle').on('click', function () {
        toggleAdminSite($, !admin_side_spread);
        return false;
    });

    $("#file-explorer").on('click', function () {
        tab.tabAdd({ "title": $(this).attr("title"), "href": this.href });
        return false;
    });

    $(".admin-nav-bar .nav_lnk").click(function () {
        var index = $(this).attr("data-index");
        $("#admin-navbar-side").html($("#siteMenu" + index).html())
            .find("a.tabUrlLink").click(function () {
                var url = this.href;
                var index = url.indexOf(":ajax");
                if(index == -1){
                    tab.tabAdd({ "title": $(this).find("cite").text(), "href": url });
                } else {
                    layPost.post(url.substring(0, index), $(this).attr("data-param"), null, null, true);
                }
            return false;
        });
        $("#admin-navbar-side").children('ul')
            .find('li.layui-nav-item').on('click', function () {
            $(this).siblings().removeClass('layui-nav-itemed');
        });
        element.render();
        if (!admin_side_spread) {
            toggleAdminSite($, true);
        }
        return false;
    });
    $(".admin-nav-bar .nav_lnk:first").click();

    //iframe自适应
    $(window).on('resize', function () {
        var detHeight = $(".layui-header").height() +
            $(".layui-tab-title").height() +
            $(".layui-footer").height() + 2;
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - detHeight);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
    }).resize();

    // 锁屏
    $(document).on('keydown', function () {
        var e = window.event;
        if (e.keyCode === 76 && e.altKey) { //alt+l
            lock($, layer, baseUrl, layPost);
        }
    });

    $('#lock').on('click', function () {
        lock($, layer, baseUrl, layPost);
    });

    // 页面操作
    $(".admin-tab-box").on('click', function () {
        $("#page-box").removeClass("layui-hide");
        $("#page-box").addClass("layui-show");
        return false;
    });

    var mouseIn = false;

    $("#page-box").mouseenter(function () {
        mouseIn = true;
    });

    $("#page-box").mouseleave(function () {
        if(mouseIn){
            $("#page-box").removeClass("layui-show");
            $("#page-box").addClass("layui-hide");
            mouseIn = false;
        }
    });

    // 刷新当前页面
    $(".page-refresh").on("click", function () {
        $("#page-box").removeClass("layui-show");
        $("#page-box").addClass("layui-hide");
        $(".layui-tab-content .layui-tab-item.layui-show").find("iframe")[0].contentWindow.location.reload(true);
    });

    // 关闭所有页面
    $(".page-closeAll").on("click", function () {
        $("#page-box").removeClass("layui-show");
        $("#page-box").addClass("layui-hide");
        if ($(".layui-tab-title li").length > 1) {
            $(".layui-tab-title li").each(function () {
                if ($(this).attr("lay-id") != '') {
                    element.tabDelete("admin-tab", $(this).attr("lay-id")).init();
                }
            })
        }
        $(".admin-tab-home").addClass("layui-this");
        $(".admin-tab-content div:first").removeClass("layui-hide");
        $(".admin-tab-content div:first").addClass("layui-show");
    });

    // 关闭其他页面
    $(".page-closeOther").on("click", function () {
        $("#page-box").removeClass("layui-show");
        $("#page-box").addClass("layui-hide");
        if ($(".layui-tab-title li").length > 2 && !$(".layui-tab-title li.layui-this").hasClass("admin-tab-home")) {
            $(".layui-tab-title li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("admin-tab", $(this).attr("lay-id")).init();
                }
            })
        } else if ($(".layui-tab-title li.layui-this").hasClass("admin-tab-home") && $(".layui-tab-title li").length > 1) {
            $(".layui-tab-title li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("admin-tab", $(this).attr("lay-id")).init();
                }
            })
        }
    });
});

var isShowLock = false;
function lock($, layer, baseUrl, layPost) {
    if (isShowLock) return;
    //自定页
    layer.open({
        title: false,
        type: 1,
        closeBtn: false,
        anim: 6,
        content: $('#lock-temp').html(),
        shade: 0.6,
        success: function (layero, lockIndex) {
            //如果浏览器被强制刷新的时候，身份验证还存在的情况
            layPost.post(baseUrl + 'admin/Admin/lckUI', null, function (data) {
                isShowLock = true;
            });
            //绑定解锁按钮的点击事件
            layero.find('button#unlock').on('click', function () {
                var userName = $('#lockUserName').val();
                var pwd = $('#lockPwd').val();
                if (pwd === '输入密码解锁..' || pwd.length === 0) {
                    layer.msg('请输入密码..', {icon: 2, time: 1000});
                    return;
                }
                var url = baseUrl + 'admin/Admin/unlckUI';
                var arg = {"userName": userName, "password": pwd};
                layPost.post(url, arg, function (data) {
                    isShowLock = false;
                    layer.close(lockIndex);
                }, function (data) {
                    switch (parseInt(data)) {
                        case 4: layer.msg("账号或密码输入错误！", {icon: 2}); break;
                        case 7: layer.msg("账户被锁定！", {icon: 2}); break;
                        case 8: layer.msg("操作失败，请检查网络！", {icon: 2}); break;
                        default: break;
                    }
                });
            });
        }
    });
}

function toggleAdminSite($, isSpreed) {
    var isFramePage = typeof arguments[2] !== 'undefined' ? arguments[2] : false; // 函数默认值设置。

    var $toggle = isFramePage ? $('#admin-side-toggle', window.parent.document) : $('#admin-side-toggle');
    var $body = isFramePage ? $('.layui-body', window.parent.document) : $('.layui-body');
    var $footer = isFramePage ? $('.layui-footer', window.parent.document) : $('.layui-footer');
    var $oper = isFramePage ? $('.admin-oper', window.parent.document) : $('.admin-oper');
    var $side = isFramePage ? $('.layui-side', window.parent.document) : $('.layui-side');

    $toggle.find("i").removeClass(isSpreed ? "layui-icon-spread-left" : "layui-icon-shrink-right");
    $toggle.find("i").addClass(isSpreed ? "layui-icon-shrink-right" : "layui-icon-spread-left");

    $body.animate({left: isSpreed ? sideWidth + 'px' : 0});
    $footer.animate({left: isSpreed ? sideWidth + 'px' : 0});
    $oper.animate({left: isSpreed ? sideWidth + 'px' : 0});
    $side.animate({width: isSpreed ? sideWidth + 'px' : 0});
    admin_side_spread = isSpreed;
}
