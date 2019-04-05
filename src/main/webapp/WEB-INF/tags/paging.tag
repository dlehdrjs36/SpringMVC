<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="paging" type="com.springmvc.test.web.paging.Paging" %>
<%@ attribute name="jsFunc" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty jsFunc}">
	<c:set var="jsFunc" value="go_page"></c:set>
</c:if>
<ul>
<li>이전</li>
<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i">
	<c:if test="${i != paging.page}">
		<li><a href="#" onclick="${jsFunc}(${i})">${i}</a></li>
	</c:if>
	<c:if test="${i == paging.page}">
		<li class="active">${i}</li>
	</c:if>
</c:forEach>
<li>다음</li>
</ul>