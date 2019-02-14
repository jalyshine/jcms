<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcp" uri="http://www.springframework.org/tags/content-page" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>

<%--需要参数：
    id    栏目ID
    root  站点根路径
    auto  是否自动访问
    pn    当前页码
    cnt   总页数
--%>

<%--查询栏目信息--%>
<jfp:SqlDataSource id="DB0" cmd="SELECT * FROM category WHERE id = ${ param.id }"/>
<jfp:SingleItem dataSourceID="DB0" var="category"/>
<jfp:JsonDataSource id="style" value="${ category.style }"/>

<ul class="pagination">
    <li>
        <a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}"
                  html="${ param.root }${ category.dir_name }/{1}_1.html"
                  isAuto="${ param.auto != '' }"
                  data="{${ category.id }}" />' title="首页">&lt;&lt;</a>
    </li>
    <li>
        <c:if test="${ param.pn == 1 }">
            <a href="javascript:;" title="上一页">&lt;</a>
        </c:if>
        <c:if test="${ param.pn > 1 }">
            <a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}&pn={2}"
                      html="${ param.root }${ category.dir_name }/{1}_{2}.html"
                      isAuto="${ param.auto != '' }"
                      data="{${ category.id },${ param.pn - 1 }}" />' title="上一页">&lt;</a>
        </c:if>
    </li>

    <c:forEach begin="1" end="${ param.cnt }" step="1" var="i">
        <li>
        <a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}&pn={2}"
                      html="${ param.root }${ category.dir_name }/{1}_{2}.html"
                      isAuto="${ param.auto != '' }"
                      data="{${ category.id },${ i }}" />' title="第${ i }页"
           <c:if test="${ param.pn == i }">class="active"</c:if>
        >${ i }</a>
        </li>
    </c:forEach>

    <li>
        <c:if test="${ param.pn == param.cnt }">
            <a href="javascript:;" title="下一页">&gt;</a>
        </c:if>
        <c:if test="${ param.pn < param.cnt }">
            <a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}&pn={2}"
                      html="${ param.root }${ category.dir_name }/{1}_{2}.html"
                      isAuto="${ param.auto != '' }"
                      data="{${ category.id },${ param.pn + 1 }}" />' title="下一页">&gt;</a>
        </c:if>
    </li>
    <li>
        <a href='<jcp:Url jsp="${ param.root }content/${ style.home }?id={1}&pn={2}"
                  html="${ param.root }${ category.dir_name }/{1}_{2}.html"
                  isAuto="${ param.auto != '' }"
                  data="{${ category.id },${ param.cnt }}" />' title="尾页">&gt;&gt;</a>
    </li>
</ul>




