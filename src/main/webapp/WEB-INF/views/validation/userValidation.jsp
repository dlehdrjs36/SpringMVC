<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유효성 검사</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<!--  페이지호출되면 자동으로 서버단에서 스크립트 내려받음. -->
<validator:javascript formName="userDTO" staticJavascript="false" xhtml="true" cdata="false" /> 
</head>
<body>

<h3>회원정보 등록</h3>

<!-- . 현재위치(같은 url 경로이기때문에 생략해도 됨)에서 -->
<form:form action="./" commandName="userDTO" onsubmit="return validateUserDTO(this)"> 
	id <form:input path="id"  /><br>
	pw <form:input path="password" /><br>
	<%-- name <form:input path="name" /><br>
	role <form:select path="role">
			<form:options items="${roleMap}"/> <!--  알아서 foreach 돌려줌 -->
		 </form:select> 
		 <br> 
		<select name="role">
 				<option value="">선택</option>
 			<c:forEach items="${roleMap}" var="temp">
				<option value="${temp.key}">${temp.value}</option> 
			</c:forEach>
 			</select> 
 			<br>
	 --%>
 	<input type="submit" value="등록" />
</form:form>
</body>
</html>