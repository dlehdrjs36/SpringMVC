<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>com.springmvc.test.web.fileupload 패키지- 파일 업로드</title>
</head>
<body>
<table>
<c:forEach items="${datas}" var="board">
	<tr>
		<td>${board.boardId}</td>
		<td><a href="./FileDown.do?atchFileId=${board.boardFile}">${board.boardFile}</a></td>

	</tr>
</c:forEach> 
</table>
<a href="./fileUploadView.do">메인화면 - 파일업로드</a>
</body>
</html>