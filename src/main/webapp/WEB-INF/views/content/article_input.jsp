<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<c:import url="../account.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加/修改文章</title>
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
<jfp:JsonDataSource value="{'0':'不分页','1':'自动分页','2':'手动分页'}" id="pagingStyle"/>
<c:if test="${ article.id == null }">
    <c:set value="${ base }content/Article/add" var="save"/>
</c:if>
<c:if test="${ article.id != null }">
    <c:set value="${ base }content/Article/edit" var="save"/>
</c:if>

<div class="layui-card">
    <div class="layui-card-header">
        <span class="card-title">添加 & 编辑文章</span>
        <c:import url="content_menubar.jsp">
            <c:param name="et" value="Article"/>
            <c:param name="etn" value="文章"/>
            <c:param name="cid" value="${ category.id }"/>
            <c:param name="wfs" value="${ category.workFlow.steps }"/>
        </c:import>
    </div>
    <div class="layui-card-body">
        <form:form action="${ save }" method="post" modelAttribute="article" cssClass="layui-form">
            <!-- 防止表单重复提交 -->
            <jfp:token/>
            <form:hidden path="id"/>
            <form:hidden path="articleData.articleId"/>
            <!-- 管理员添加 -->
            <form:hidden path="sysAdd" value="true"/>
            <!-- 所属栏目 -->
            <form:hidden path="categoryId" value="${ category.id }"/>
            <!-- 用于栏目权限控制 -->
            <input type="hidden" name="cid" value="${ category.id }">

            <div class="layui-form-item">
                <label class="layui-form-label">所属栏目</label>

                <div class="layui-input-inline">
                    <label class="layui-form-label"
                           style="background-color: #E6E6E6; text-align: center;">${ category.name }</label>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">肩标题</label>

                <div class="layui-input-block">
                    <form:input path="title1" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文章标题</label>

                <div class="layui-input-block">
                    <form:input path="title" autocomplete="off" lay-verify="title" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">副标题</label>

                <div class="layui-input-block">
                    <form:input path="title2" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">关键词</label>

                <div class="layui-input-inline">
                    <form:input path="keywords" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">多关键词之间用空格或“,”隔开</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文章来源</label>
                <div class="layui-input-inline">
                    <form:select path="articleData.copyFromId">
                        <option value="0">请选择来源</option>
                        <form:options items="${ copyFroms }" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文章作者</label>

                <div class="layui-input-inline">
                    <form:input path="author" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">多作者之间用空格或“,”隔开</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">作者单位</label>

                <div class="layui-input-block">
                    <form:input path="authorUnit" autocomplete="off" class="layui-input"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文章摘要</label>

                <div class="layui-input-block">
                    <form:textarea path="description" class="layui-textarea"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">文章内容</label>

                <div class="layui-input-block">
                    <%--存放编辑器附件地址--%>
                    <input type="hidden" name="editorAttachment"/>
                    <form:textarea path="articleData.content" cssClass="ckeditor"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">分页方式</label>

                <div class="layui-input-inline">
                    <form:select items="${ pagingStyle }" path="articleData.pageType"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">最大字符数</label>

                <div class="layui-input-inline">
                    <form:input path="articleData.maxCharPerPage" autocomplete="off" lay-verify="number"
                                class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">每页最大字符数（仅自动分页时有效）</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">推荐位</label>

                <div class="layui-input-block">
                    <form:checkboxes items="${ recommendPositions }" itemLabel="name" itemValue="id"
                                     path="articleData.positionIds"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">焦点图</label>

                <div class="layui-input-block layui-upload">
                    <input type="checkbox" lay-filter="upload" title="上传原图">
                    <form:hidden path="thumb"/>
                    <div class="layui-upload-list">
                        <img id="thumb-img" style="height: 100px"
                        <c:if test="${ empty article.thumb }">
                             src="${ base }assets/css/img/upload_bk.png">
                        </c:if>
                        <c:if test="${ !empty article.thumb }">
                            src="${ host }/${ article.thumb }">
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>

                <div class="layui-input-inline">
                    <form:input path="publishTimeStr" autocomplete="off" class="layui-input" readonly="readonly"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">链接地址</label>

                <div class="layui-input-inline">
                    <form:input path="url" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <form:checkbox path="isLink" title="转向链接"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">允许评论</label>

                <div class="layui-input-inline">
                    <form:checkbox path="articleData.allowComment" title="允许"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">阅读收费</label>

                <div class="layui-input-inline">
                    <form:input path="articleData.readPoint" autocomplete="off" lay-verify="number"
                                class="layui-input"/>
                </div>
                <div class="layui-form-mid layui-word-aux">点/元</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">阅读权限</label>

                <div class="layui-input-block">
                    <form:checkboxes items="${ memberGroups }"
                                     path="articleData.groupIds" itemLabel="name" itemValue="id"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">稿件状态</label>

                <div class="layui-input-inline">
                    <c:choose>
                        <c:when test="${ category.workFlow.steps == 1 }">
                            <jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','99':'通过'}"/>
                        </c:when>
                        <c:when test="${ category.workFlow.steps == 2 }">
                            <jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','2':'二审','99':'通过'}"/>
                        </c:when>
                        <c:when test="${ category.workFlow.steps == 3 }">
                            <jfp:JsonDataSource id="statusList"
                                                value="{'0':'退稿','1':'一审','2':'二审','3':'三审','99':'通过'}"/>
                        </c:when>
                        <c:when test="${ category.workFlow.steps == 4 }">
                            <jfp:JsonDataSource id="statusList"
                                                value="{'0':'退稿','1':'一审','2':'二审','3':'三审','4':'四审','99':'通过'}"/>
                        </c:when>
                        <c:otherwise>
                            <jfp:JsonDataSource id="statusList" value="{'0':'退稿','99':'通过'}"/>
                        </c:otherwise>
                    </c:choose>
                    <form:select path="status" items="${ statusList }"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">添加投票</label>

                <div class="layui-input-inline">
                    <input type="text" name="voteId" autocomplete="off" class="layui-input"/>
                </div>
                <div class="layui-input-inline">
                    <input type="button" value="选择已有投票" class="layui-btn">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit id="saveBtn" class="layui-btn">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/fixed.js"></script>
<script src="${ base }assets/plugins/ckeditor/ckeditor.js"></script>
<script>
    var editor = CKEDITOR.replace('articleData.content', {
        adminUrl: '${ base }',
        imageUploadUrl: '${ host }/upload?type=ill',
        fileUploadUrl: '${ host }/upload',
        account: '${ host_account }',
        password: '${ host_password }',
        baseHref: '${ host }/'
    });

    layui.config({
        base: '${ base }assets/js/'
    }).use(['form', 'laydate', 'layPost', 'element'], function () {
        var $ = layui.$, form = layui.form, laydate = layui.laydate, layPost = layui.layPost;

        laydate.render({elem: '#publishTimeStr', type: 'datetime'});

        var uploadInst = layPost.image('thumb', 'thumb', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.on('checkbox(upload)', function(data){
            if(data.elem.checked){
                uploadInst.config.url = '${ host }/upload?type=customer';
            } else {
                uploadInst.config.url = '${ host }/upload?type=thumb';
            }
        });

        form.verify({
            title: function (value, item) {
                if ($.trim(value) == "") {
                    return "文章标题不能为空！";
                }
                var flag = false;
                $.ajaxSettings.async = false; // 异步提交
                $.post("${ base }content/Article/check", {'time': new Date(), 'ttl': value}, function (data) {
                    flag = data != 0 && data != $("#id").val();
                });
                $.ajaxSettings.async = true; // 恢复同步提交
                if (flag) {
                    return "该标题已经被占用！";
                }
            }
        });

        $("#saveBtn").on("click", function () {
            var $content = $("<div></div>").html(editor.document.getBody().getHtml());
            var filePath = "";
            $content.find("[src]").each(function (index) {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    filePath += $(this).attr("src") + ",";
                }
            });
            $(":hidden[name='editorAttachment']").val(filePath.substring(0, filePath.length - 1));
            $("form").submit();
            return false;
        });
    });
</script>
</body>
</html>