<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="boardPaging" type="com.springmvc.test.web.comment.BoardPagingDTO" %>
<%@ attribute name="jsFunc" required="false" type="java.lang.String" %>
<c:if test="${empty jsFunc}">
	<c:set var="jsFunc" value="go_page"></c:set>
</c:if>


<%-- 	<c:url var="action" value="/PointList.po"/> --%>
	<div class="text-center">
		<!-- <ul class="pagination">	 -->
			<!-- <li> --><a href="#" onclick="${jsFunc}(1);">처음</a><!-- </li> -->
			<c:if test="${boardPaging.prev}">
				<!-- <li> --><a href="#" onclick="${jsFunc}(${boardPaging.prevPageno});">이전으로</a><!-- </li> -->
			</c:if>
			<c:forEach begin="${boardPaging.beginPage}" end="${boardPaging.endPage}" step="1" var="index">
    			<c:choose>
        			<c:when test="${boardPaging.page==index}">
        				<!-- <li> --><a>${index}</a><!-- </li> -->
        			</c:when>
        			<c:otherwise>
            			<!-- <li> --><a href="#" onclick="${jsFunc}(${index});">${index}</a><!-- </li> -->
        			</c:otherwise>
    			</c:choose>
			</c:forEach>
			<c:if test="${boardPaging.next}">
				<!-- <li> --><a href="#" onclick="${jsFunc}(${boardPaging.nextPageno});">다음으로</a><!-- </li> -->
			</c:if>
			<!-- <li> --><a href="#" onclick="${jsFunc}(${boardPaging.totalPage});">마지막</a><!-- </li> -->
		<!-- </ul> -->
	</div>
