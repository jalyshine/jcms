<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--查询当前栏目，获取静态设置--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="setting" value="${ category.setting }"/>
<%--查询当前站点，设置模板样式的基本路径，和主机站点的基本路径 --%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site LEFT JOIN site_info ON id = site_id
                                WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="${ category.name }" />

<jcp:ContentPage masterPagePath="/template/company1/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ setting.metaTitle }</title>
        <meta name="keywords" content="${ setting.metaKeywords }">
        <meta name="description" content="${ setting.metaDescription }"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">
        <div class="conPg">
            <div class="banner">
                <img src="${ site.domain }/${ category.image }"/>
            </div>
            <div class="container padT80">
                <div class="map">
                    <input type="hidden" id="position" value="${ site.position }" />
                    <input type="hidden" id="title" value="${ site.manager }" />
                    <input type="hidden" id="address" value="${ site.address }" />
                    <iframe src="${ root }assets/map/map.html"></iframe>
                    <div class="loader">
                        <img src="${ root }assets/images/loader.gif"/>
                    </div>
                </div>
                <div class="address row padT80 padB80">
                    <div class="col-sm-6 col-xs-12">
                        <section class="title">
                            <h2>联系我们</h2>
                        </section>
                        <ul class="padT80">
                            <li><span class="glyphicon glyphicon-phone-alt"></span>全国服务热线：${ site.telephone }</li>
                            <li><span class="glyphicon glyphicon-map-marker"></span>公司地址：${ site.address }</li>
                            <li><span class="glyphicon glyphicon-envelope"></span>公司邮箱：${ site.email }</li>
                            <li><span class="glyphicon glyphicon-phone"></span>联系电话：${ site.phone }</li>
                        </ul>
                    </div>
                    <div class="col-sm-6 col-xs-12 padT80">
                        <form action="${ site.domain }/jcms/admin/SiteContact/contact" method="post">
                            <input type="hidden" name="siteId" value="${ site.id }" />
                            <input placeholder="姓名" type="name" name="name" id="name"/>
                            <input placeholder="邮箱" type="email" name="email" id="email"/>
                            <input placeholder="电话" type="text" name="phone" id="text"/>
                            <textarea placeholder="消息" rows="5" name="content"></textarea>
                        </form>
                        <a class="btn btn-primary" id="sendBtn">发送</a>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(function () {
                $("#sendBtn").click(function () {
                    $.ajax({
                        type: "POST"
                        , url: $("form").attr("action")
                        , data: $("form").serialize()
                        , dataType: "json"
                        , cache: false
                        , success: function (res) {
                            alert("谢谢合作！");
                        }
                    });
                    return false;
                });
            })
        </script>
    </jcp:Content>
</jcp:ContentPage>