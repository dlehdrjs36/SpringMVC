<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>

	<li><a href="${pageContext.request.contextPath}">메인 화면</a></li>
	<c:if test="${sessionScope.authUser eq null}">
		<li><a href="${pageContext.request.contextPath}/login.do">로그인</a></li>
	</c:if>
	<c:if test="${sessionScope.authUser ne null}">
		<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
	</c:if>
	<li><a href="#">회원가입</a></li>
</ul>