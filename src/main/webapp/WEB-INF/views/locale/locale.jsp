<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri ="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 다국어 처리 - locale, <spring:message code="title.sample.title"/></title>
</head>
<body>
	<form action="<c:url value= "/fileUpload.do" />" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<input type="file" name="uploadFile" /> 
		<input type="submit" />
	</form>
	<a href="localeView.do?lang=en" > 
	<spring:message code="message.language.en"/></a>&nbsp;&nbsp; 
	<a href="localeView.do?lang=ko"> 
	<spring:message code="message.language.ko"/></a> 
	<hr>
	<ul>
		<li><a href="./broadcast.do"><spring:message code="li.a1"/></a></li>
		<li><a href="./getMemberList.do"><spring:message code="li.a2"/></a></li>
		<li><a href="./getJsonMemberList.do"><spring:message code="li.a3"/></a></li>
		<li><a href="./getJsonMemberList2.do"><spring:message code="li.a4"/></a></li>
	</ul>
</body>
</html>