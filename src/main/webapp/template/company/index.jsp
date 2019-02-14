<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>

<%--传给母版--%>
<jcp:Param name="root" value=""/>
<jcp:Param name="siteId" value="${ param.id }"/>
<jcp:Param name="menuItemName" value="首页"/>

<%--查找站点信息--%>
<jfp:SqlDataSource id="DB0"
                   cmd="SELECT * FROM site LEFT JOIN site_info ON id = site_id
                        WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="site"/>

<jcp:ContentPage masterPagePath="/template/company/master.jsp">

    <%--网页头部--%>
    <jcp:Content contentPlaceHolderId="head">
        <title>${ site.title }</title>
        <meta name="keywords" content="${ site.keywords }">
        <meta name="description" content="${ site.description }"/>
    </jcp:Content>

    <%--网页主体--%>
    <jcp:Content contentPlaceHolderId="pageContent">
        <!--产品服务-->
        <jfp:SqlDataSource id="DB1"
                           cmd="SELECT * FROM article a LEFT JOIN category c ON category_id = c.id
                                WHERE c.name = '产品服务' AND site_id = ${ site.id }
                                ORDER BY publish_time DESC limit 0, 5"/>
        <div class="MainAnli">
            <div class="AnList">
                <ul>
                    <jfp:Repeater dataSourceID="DB1" var="item">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <li>
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                              html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                              isAuto="${ param.auto != null }"
                                              data="{${ item.id }}" />'>
                                <img src="${ site.domain }/${ item.thumb }" width="201" height="130"/>
                                <p>${ item.title }</p>
                            </a>
                        </li>
                    </jfp:Repeater>
                </ul>
            </div>
        </div>
        <!--关于我们-->
        <jfp:SqlDataSource id="DB2"
                           cmd="SELECT * FROM single_page s LEFT JOIN category c ON category_id = c.id
                                WHERE c.name = '关于我们' AND site_id = ${ site.id }"/>
        <jfp:SingleItem dataSourceID="DB2" var="item"/>
        <div class="MainAbout">
            <div class="about_title"></div>
            <div class="about_1200">
                <div class="about_sp">
                    <img src="${ site.domain }/${ item.thumb }"/>
                </div>
                <div class="about_jj">${ item.content }</div>
            </div>
        </div>
        <!--新闻资讯-->
        <div class="MainNews">
            <div class="News_1200">
                <div class="News_title">
                    <div class="NewsTitleimg"></div>
                        <%--查找新闻资讯的子栏目--%>
                    <jfp:SqlDataSource id="DB3"
                                       cmd="SELECT * FROM category x LEFT JOIN category y ON x.parent_id = y.id
                                            WHERE y.name='新闻资讯' AND y.site_id = ${ site.id } "/>
                    <div class="NewsOtherList">
                        <jfp:Repeater dataSourceID="DB3" var="item" index="i">
                            <jfp:JsonDataSource id="style" value="${ item.style }"/>
                            <c:if test="${ i > 0 }">　/　</c:if>
                            <a href='<jcp:Url jsp="${ root }content/${ style.home }?id={1}"
                                               html="${ root }${ item.dir_name }/{1}_1.html"
                                               isAuto="${ param.auto != null }"
                                               data="{${ item.id }}" />'>
                                    ${ item.name }
                            </a>
                        </jfp:Repeater>
                    </div>
                </div>
                <div class="NewsListMain">
                    <jfp:SqlDataSource id="DB4"
                                       cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                            FROM article a LEFT JOIN category c ON category_id = c.id
                                            WHERE c.name IN ('企业动态','行业新闻') AND site_id = ${ site.id }
                                            ORDER BY publish_time DESC limit 0, 6"/>
                    <div class="NewsHang">
                        <jfp:Repeater dataSourceID="DB4" var="item">
                            <jfp:JsonDataSource id="style" value="${ item.style }"/>
                            <dl>
                                <dt>
                                    <img src="${ site.domain }/${ item.thumb }" width="150" height="100"/>
                                </dt>
                                <dd>
                                    <h4><a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                          html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                          isAuto="${ param.auto != null }"
                                                          data="{${ item.id }}" />'>${ item.title }</a></h4>
                                    <p>发布日期：${ item.time }</p>
                                    <p>
                                        <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                          html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                          isAuto="${ param.auto != null }"
                                                          data="{${ item.id }}" />'>
                                            更多详情>>
                                        </a>
                                    </p>
                                </dd>
                            </dl>
                        </jfp:Repeater>
                    </div>
                    <div class="NewsQiYe">
                        <div class="NewsQiYeTitle"></div>
                        <jfp:SqlDataSource id="DB5"
                                           cmd="SELECT *, DATE_FORMAT(publish_time,'%Y-%m-%d') time
                                           FROM article a LEFT JOIN category c ON category_id = c.id
                                           WHERE c.name = '企业公告' AND site_id = ${ site.id }
                                           ORDER BY publish_time DESC limit 0, 4"/>
                        <div class="QiyeList">
                            <ul>
                                <jfp:Repeater dataSourceID="DB5" var="item">
                                <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                <li>
                                <h5><a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ item.id }}" />'>${ item.title }</a></h5>
                                <p>${ item.time }　
                                    <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ item.id }}" />'>
                                        详情>>
                                    </a>
                                </p>
                                </li>
                                </jfp:Repeater>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--业务范围-->
        <jfp:SqlDataSource id="DB6" cmd="SELECT * FROM category WHERE name='业务范围' AND site_id = ${ site.id }"/>
        <jfp:SingleItem dataSourceID="DB6" var="category"/>
        <div class="Mainyewu">
            <div class="YeWu1200">
                <div class="YeWuTitle"></div>
                <h1>坚持货正品、费率低、服务号、售后有保障</h1>
                <p>${ category.description }</p>
                <jfp:SqlDataSource id="DB7"
                                   cmd="SELECT * FROM word w LEFT JOIN category c ON category_id = c.id
                                        WHERE c.name = '业务范围' AND site_id = ${ site.id }
                                        ORDER BY update_time DESC limit 0, 6"/>
                <ul>
                    <jfp:Repeater dataSourceID="DB7" var="item" index="i">
                        <jfp:JsonDataSource id="style" value="${ item.style }"/>
                        <li>
                            <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ item.id }}" />'>
                                <img class="tc" src="${ site.domain }/${ item.icon }"/> ${ item.title }
                            </a>
                        </li>
                    </jfp:Repeater>
                </ul>
            </div>
        </div>
        <!--荣誉资质和招贤纳士-->
        <div class="Mainryzp">
            <div class="ryzp_1200">
                <div class="ryMain">
                    <div class="ryzptitle1"></div>
                    <jfp:SqlDataSource id="DB8"
                                       cmd="SELECT * FROM tip t LEFT JOIN tip_type y ON t.type_id = y.id
                                        WHERE y.name = '荣誉资质' AND y.site_id = ${ site.id }
                                        ORDER BY update_time DESC limit 0, 4"/>
                    <ul>
                        <jfp:Repeater dataSourceID="DB8" var="item">
                            <li><img src="${ site.domain }/${ item.icon }"/></li>
                        </jfp:Repeater>
                    </ul>
                </div>
                <jfp:SqlDataSource id="DB9" cmd="SELECT * FROM category WHERE name='招贤纳士'"/>
                <jfp:SingleItem dataSourceID="DB9" var="category"/>
                <jfp:SqlDataSource id="DB10"
                                   cmd="SELECT *, DATE_FORMAT(update_time,'%Y-%m-%d') time
                                        FROM word w LEFT JOIN category c ON category_id = c.id
                                        WHERE c.name = '招贤纳士' AND site_id = ${ site.id }
                                        ORDER BY update_time DESC limit 0, 5"/>
                <div class="zpMain">
                    <div class="ryzptitle2"></div>
                    <p>${ category.description }</p>
                    <div class="zpList">
                        <ul>
                            <jfp:Repeater dataSourceID="DB10" var="item">
                                <jfp:JsonDataSource id="style" value="${ item.style }"/>
                                <li>· <a href='<jcp:Url jsp="${ root }content/${ style.show }?id={1}"
                                                      html="${ root }${ item.dir_name }/show/${ item.timeDir }/{1}.html"
                                                      isAuto="${ param.auto != null }"
                                                      data="{${ item.id }}" />'>
                                        ${ item.title }
                                </a><span>${ item.time }</span></li>
                            </jfp:Repeater>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--联系我们-->
        <div class="MainContact">
            <div class="Contact_1200">
                <div class="ContactTitle"></div>
                <div class="ContactContent">
                    <div class="Contact_left">
                        <div class="ContactTitleImg"></div>
                        <table>
                            <tr>
                                <td class="tdwidth">联系电话：</td>
                                <td>${ site.telephone }</td>
                            </tr>
                            <tr>
                                <td class="tdwidth">传真：</td>
                                <td>${ site.fax }</td>
                            </tr>
                            <tr>
                                <td class="tdwidth">电子邮箱：</td>
                                <td>${ site.email }</td>
                            </tr>
                            <tr>
                                <td class="tdwidth">邮编：</td>
                                <td>${ site.post_code }</td>
                            </tr>
                            <tr>
                                <td class="tdwidth">公司地址：</td>
                                <td>${ site.address }</td>
                            </tr>
                            <tr>
                                <td class="tdwidth">企业网址：</td>
                                <td>${ site.url }</td>
                            </tr>
                        </table>
                    </div>
                    <div class="Contact_right">
                        <img src="${ site.domain }/${ site.image }"/>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function scroll(top, name, obj) {
                if ($(window).scrollTop() > top) {
                    obj.each(function (i) {
                        $(this).attr("id", name + (i + 1));
                    });
                } else {
                    obj.each(function () {
                        $(this).removeAttr("id");
                    });
                }
            }
            $(window).scroll(function () {
                scroll(200, "al", $(".AnList ul li"));
                scroll(2100, "tc", $(".tc"));
                scroll(2700, "al", $(".ryMain ul li"));
            });
        </script>
    </jcp:Content>

</jcp:ContentPage>