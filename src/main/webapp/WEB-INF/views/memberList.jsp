<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록 - 페이징 예제</title>
</head>
<body>
<script>
	function go_page(p) {
		document.frm.page.value=p;
		document.frm.submit();
		// go_page(p) 함수가 실행되면 form에서 정의한 page값이 전달된다.
	}
</script>
<form action="getMemberList.do" name="frm">
	<input type="hidden" name="page" value="1">
</form>
<table>
<c:forEach items="${datas}" var="emp">
	<tr>
		<td>${emp.id}</td>
		<td>${emp.name}</td>
		<td>${emp.role}</td>
	</tr>
</c:forEach> 
</table>
<my:paging paging="${paging}"/>
<a href="./fileUploadView.do">메인화면 - 파일업로드</a>
</body>
</html>

