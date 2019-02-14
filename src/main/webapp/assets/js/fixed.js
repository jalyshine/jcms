layui.use('jquery', function () {
    var $ = layui.$;
    $("span :radio").each(function (i) {
        var ttl = $(this).nextAll("label:first").text();
        $(this).attr("title", ttl);
        $(this).nextAll("label:first").remove();
    });
    $("span :checkbox").each(function (i) {
        var ttl = $(this).nextAll("label:first").text();
        $(this).attr("title", ttl);
        $(this).nextAll("label:first").remove();
    });
});