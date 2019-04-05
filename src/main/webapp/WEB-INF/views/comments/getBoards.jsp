<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<title>board/getBoard.jsp</title>
</head>
<body>

<table border="1" style="width: 1200px;">
	<tr><!-- <td>SEQ</td> -->
		<td>TITLE</td>
		<td>WRITER</td>
		<!-- <td>CONTENT</td> -->
		<td>REGDATE</td>
		<td>CNT</td>
		<!-- <td>uploadFileName</td> -->
	</tr>
	<c:forEach items="${list}" var="board">
		<tr>
			<%-- <td>${board.boardId}</td> --%>
			<td><a href="./getBoard.do?boardId=${board.boardId}"> ${board.boardTitle}</a></td>
			<td>${board.boardWriter}</td>
			<%-- <td>${board.boardContent}</td> --%>
			<td>${board.boardDate}</td>
			<td>${board.boardHit}</td>
			<%-- <td>${board.boardFile}</td> --%>
			<br>
			<td><a href="./getBoardAndComment.do?boardId=${board.boardId}"> 댓글테스트 중${board.boardTitle}</a></td>
		</tr>
	</c:forEach>
</table>

<script>
	function go_page(p) {
		document.frm.page.value=p;
		document.frm.submit();
		// go_page(p) 함수가 실행되면 form에서 정의한 page값이 전달된다.
	}
</script>
<script type="text/javascript" src="./resources/js/comment.js"></script>
<form action="getBoardList.do" name="frm">
	<input type="hidden" name="page" value="1">
</form>
<my:boardPaging boardPaging="${paging}"/> <br>
<a href="./"> 메인화면</a>	
	
<a href="getBoardAndComment.do">댓글 예제 테스트중</a>
</body>
</html>