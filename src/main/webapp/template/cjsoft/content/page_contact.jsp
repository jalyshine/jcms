<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--查询当前栏目，获取静态设置--%>
<jfp:SqlDataSource id="DB0"
                   cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="meta" value="${ category.meta }"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>

<%--查询当前站点，设置模板样式的基本路径，和主机站点的基本路径 --%>
<jfp:SqlDataSource id="DB1"
                   cmd="SELECT * FROM site LEFT JOIN site_info ON id = site_id
                        WHERE id = ${ category.site_id }" />
<jfp:SingleItem dataSourceID="DB1" var="site"/>
<%--向母版页传参--%>
<jcp:Param name="root" value="../"/>
<jcp:Param name="siteId" value="${ site.id }" />
<jcp:Param name="menuItemName" value="联系" />

<jcp:ContentPage masterPagePath="/template/cjsoft/master.jsp">

    <jcp:Content contentPlaceHolderId="head">
        <title>${ meta.title }</title>
        <meta name="keywords" content="${ meta.keyword }">
        <meta name="description" content="${ meta.description }"/>
    </jcp:Content>

    <jcp:Content contentPlaceHolderId="pageContent">
        <!-- 标题栏 -->
        <section id="page-title"  style="background-image: url(${ site.domain }/${ category.banner });">
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

        <!--联系方式-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>联系方式 · <span>CONTACT METHOD</span></h2>
                            <p>无论你是正准备开创自己的事业，还是想发展壮大现有的企业，
                                都有知识丰富的专业人士与你全程协作。<br/>指导、服务、技术支持，都在这里。</p>
                        </section>
                    </section>
                </div>

                <div class="row">
                    <article class="col-md-3 col-sm-6">
                        <section class="method_item triggerAnimation animated" data-animate="fadeInLeft">
                            <i class="i_email"></i>
                            <br/><br/><br/>${ site.email }
                        </section>
                    </article>

                    <article class="col-md-3 col-sm-6">
                        <section class="method_item triggerAnimation animated" data-animate="fadeInLeft">
                            <i class="i_tel"></i><br/><br/><br/>${ site.telephone }
                        </section>
                    </article>

                    <article class="col-md-3 col-sm-6">
                        <section class="method_item triggerAnimation animated" data-animate="fadeInLeft">
                            <i class="i_mobile"></i><br/><br/><br/>${ site.phone }
                        </section>
                    </article>

                    <article class="col-md-3 col-sm-6">
                        <section class="method_item triggerAnimation animated" data-animate="fadeInLeft">
                            <i class="i_qq"></i><br/><br/><br/>${ site.qq }
                        </section>
                    </article>
                </div>

            </div>
        </section>

        <!--地图-->
        <section class="page-content">
            <div class="map">
                <input type="hidden" id="position" value="${ site.position }"/>
                <iframe src="${ root }assets/map/map.html"></iframe>
            </div>
        </section>

        <!--提交需求-->
        <section class="page-content">
            <div class="container">
                <div class="row">
                    <section class="col-md-12">
                        <section class="heading-centered triggerAnimation animated" data-animate="pulse">
                            <h2>有需求？找我们！</h2>
                            <p>无论你是正准备开创自己的事业，还是想发展壮大现有的企业，都有知识丰富的专业人士与你全程协作。<br/>
                                指导、服务、技术支持，都在这里。 我们的团队就是你的技术支持团队。只要你有需求，就会有我们的一臂之力。</p>
                        </section>
                    </section>
                </div>
                <div class="row">
                    <section class="col-md-12 triggerAnimation animated" data-animate="fadeInRight">
                        <form action="${ site.domain }/jcms/admin/SiteContact/contact" method="post" class="wpcf7">
                            <fieldset class="half">
                                <label><span class="text-color">*</span> 姓名：</label>
                                <input type="text" name="name" class="wpcf7-text" id="contact-name"/>
                            </fieldset>

                            <fieldset class="half">
                                <label><span class="text-color">*</span> 手机：</label>
                                <input type="email" name="phone" id="contact-email" class="wpcf7-text"/>
                            </fieldset>

                            <fieldset class="half">
                                <label><span class="text-color"></span> 感兴趣的服务：</label>
                                <input type="text" name="service" class="wpcf7-text" id="contact-service"/>
                            </fieldset>

                            <fieldset class="half">
                                <label><span class="text-color"></span> 大概预算：</label>
                                <input type="text" name="budget" id="contact-budget" class="wpcf7-text"/>
                            </fieldset>

                            <fieldset class="full">
                                <label><span class="text-color">*</span> 需求描述：</label>
                                <textarea rows="5" name="description" class="wpcf7-textarea" id="contact-message"></textarea>
                            </fieldset>

                            <input type="button" id="sendBtn" class="btn btn-default" value="提交需求"/>
                        </form>
                    </section>
                </div>
            </div>
        </section>

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