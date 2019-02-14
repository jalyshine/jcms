<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--查询当前栏目，获取静态设置--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="meta" value="${ category.meta }"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>
<%--查询当前站点，设置模板样式的基本路径，和主机站点的基本路径 --%>
<jfp:SqlDataSource id="DB1" cmd="SELECT * FROM site WHERE id = ${ category.site_id }"/>
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="服务" />

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">

        <!-- 标题栏 -->
        <section id="page-title" style="background-image: url(${ site.domain }/${ category.banner });">
            <div class="container">
                <h1>${ category.name }</h1>
                <div class="breadcrumb-container">
                    <span>您的位置: </span>
                    <ul class="breadcrumb">
                        <li><a href='<jcp:Url jsp="${ root }index.jsp?id=${ site.id }"
                                              html="${ root }index.html"
                                              isAuto="${ param.auto != null }"/>'>首页</a></li>
                        <li><a href="javascript:;">${ category.name }</a></li>
                    </ul>
                </div>
            </div>
        </section>

        <!-- 服务项目 -->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <article class="col-md-12">
                        <div class="triggerAnimation animated" data-animate="fadeInUp">
                            <section class="heading-centered triggerAnimation animated" data-animate="fadeInUp">
                                <h2>服务项目 · <span>SERVICE PROJECT</span></h2>
                                <p>我们提供数字平台建设与数字产品设计运营的全产业链服务，从设计制作到开发运营，一站式帮您搞定。</p>
                            </section>
                        </div>
                    </article>
                </div>

                <jfp:SqlDataSource id="DB2"
                                   cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                        WHERE y.name = '服务项目' AND y.site_id = ${ site.id }" />
                <div class="row">
                    <jfp:Repeater dataSourceID="DB2" var="item">
                        <article class="col-md-3 col-sm-6">
                            <div class="triggerAnimation animated" data-animate="fadeInLeft">
                                <section class="service-img-box">
                                    <img src="${ site.domain }/${ item.icon }" class="blur" />
                                    <div class="service-img-desc clearfix">
                                        <h4>${ item.title }</h4>
                                        <p>${ item.content }</p>
                                    </div>
                                </section>
                            </div>
                        </article>
                    </jfp:Repeater>
                </div>
            </div>
        </section>

        <!--服务范围-->
        <section class="page-content parallax parallax-7">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="fadeInUp">
                            <h2>服务范围 · <span>SERVICE SCOPE</span></h2>
                            <p>用前沿的思维制作交互式用户体验的高端网站，正是我们的优势所在。</p>
                        </section>
                    </section>
                </div>

                <jfp:SqlDataSource id="DB3"
                                   cmd="SELECT c.thumb c_thumb, c.name c_name, c.description c_desc,
                                        c.style c_style, c.dir_name c_dir_name, c.id c_id, c.type c_type, c.url c_url
                                        FROM category c LEFT JOIN category p ON c.parent_id = p.id
                                        WHERE p.name = '服务' AND p.site_id = ${ site.id }" />
                <div class="row service-scope">
                    <jfp:Repeater dataSourceID="DB3" var="item" index="i">
                        <jfp:JsonDataSource id="service_style" value="${ item.c_style }"/>
                        <div class="col-md-6 col-xs-12">
                            <div
                                    <c:if test="${ i<2 }">class="service-scope-title fl"</c:if>
                                    <c:if test="${ i>=2 }">class="service-scope-title fr"</c:if>
                            >
                                <c:if test="${ item.c_type == 2 }">
                                    <a href='<jcp:Url jsp="${ root }content/${ service_style.page }?id={1}"
                                                  html="${ root }${ item.c_dir_name }/{1}_1.html"
                                                  isAuto="${ param.auto != null }"
                                                  data="{${ item.c_id }}" />'>
                                        <img src="${ site.domain }/${ item.c_thumb }">
                                        <div class="service-scope-bk"></div>
                                        <h4>${ item.c_name }</h4>
                                    </a>
                                </c:if>
                                <c:if test="${ item.c_type == 3 }">
                                    <a href='${ item.c_url }'>
                                        <img src="${ site.domain }/${ item.c_thumb }">
                                        <div class="service-scope-bk"></div>
                                        <h4>${ item.c_name }</h4>
                                    </a>
                                </c:if>
                            </div>
                            <div
                                    <c:if test="${ i<2 }">class="service-scope-desc bg-color fr"</c:if>
                                    <c:if test="${ i>=2 }">class="service-scope-desc bg-color fl"</c:if>
                            >
                                <p>${ item.c_desc }</p>
                                <span>0${ i }</span>
                            </div>
                        </div>
                    </jfp:Repeater>
                </div>
            </div>
        </section>

        <!--合作流程-->
        <section  class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="fadeInUp">
                            <h2>合作流程 · <span>COOPERATION PROCESS</span></h2>
                            <p>现在就开始执行，就会有我们的一臂之力。 您的网站所需要的一切服务，都已包含在内。 </p>
                        </section>
                    </section>
                </div>
            </div>

            <jfp:SqlDataSource id="DB4"
                               cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON type_id = y.id
                                    WHERE y.name = '合作流程' AND y.site_id = ${ site.id }" />
            <div class="container">
                <div class="row">
                    <jfp:Repeater dataSourceID="DB4" var="item">
                        <div class="col-md-2 col-sm-4 col-xs-6 service-process-item">
                            <img src="${ site.domain }/${ item.icon }">
                            <div>${ item.title }</div>
                            <p>${ item.description }</p>
                        </div>
                    </jfp:Repeater>
                </div>

                <jfp:SqlDataSource id="DBx" cmd="SELECT * FROM category WHERE name = '联系我们' AND site_id = ${ site.id }"/>
                <jfp:SingleItem dataSourceID="DBx" var="category_contact" />
                <jfp:JsonDataSource id="contact_style" value="${ category_contact.style }"/>
                <div class="row">
                    <a class="btn btn-default btn-empty" data-animate="fadeInUp"
                       href='<jcp:Url jsp="${ root }content/${ contact_style.page }?id={1}"
                                              html="${ root }${ category_contact.dir_name }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ category_contact.id }}" />'>马上联系</a>
                </div>
            </div>
        </section>

    </jcp:Content>
</jcp:ContentPage>