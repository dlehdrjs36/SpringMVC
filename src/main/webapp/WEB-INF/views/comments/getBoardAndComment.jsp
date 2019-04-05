<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
$(function(){ 
	var ContextPath = '${pageContext.request.contextPath}';
	var seq;
	//댓글 객체
	const comment = {
			//댓글 등록완료 표시
			create : 	function(datas) {
							var div = $('<div>');
							var str = 	 "<strong class='commentName'> 이름 : " + datas.name + "</strong> <br>" 
								        +"<span class='commentContent'> 내용 : " + datas.content +"</span> <br>"
										+"<button type=\"button\" class=\"updateComment\">수정</button>"
										+"<button type=\"button\" class=\"deleteComment\">삭제</button>";
							
							div.attr("id", "c"+datas.seq);
							div[0].commentData = datas;
							div.html(str);
							/* console.log(div[0].innerHtml); */
							$("#commentList").append(div);
							$("#CommentForm")[0].reset();
							
							return div;
						},
	
			list : 		function() {
							var param = { boardId : '${param.boardId}' };
							$.ajax({
								url : ContextPath+"/getCommentsList",
								data : param,
								success : function(datas) {
									for(var i=0 ; i< datas.length ; i++)
										$("#commentList").append(comment.create(datas[i]));
								}
							});
						},
						
			add : 		function() {
							var param = $("#CommentForm").serialize(); // 해당하는 필드들은 name 속성을 가지고 있어야 한다.
							console.log("등록");
							$.ajax({
								url : ContextPath+"/insertComments",
								data : param,
								success : function(datas) {
									comment.create(datas);
								}
							});	
						},
			updateView : function(event) {
				seq = $(event).parent().get(0).commentData.seq;
				var name =  $(event).parent().get(0).commentData.name;
				var content =  $(event).parent().get(0).commentData.content;
				
			/* 	console.log("aaaaaa");
				console.log($(event).parent()); */
				$(event).parent().append($("#UpdateForm"));
				
				$("#UpdateForm [name=seq]").val(seq);
				$("#UpdateForm [name=name]").val(name);
				$("#UpdateForm [name=content]").val(content);
				
				//console.log($(event).parent().get(0).commentData.seq);
				$("#UpdateForm").css("display", "block");		 	
			},
			update	 : function() {
							var param = $("#UpdateForm").serialize();
							$.ajax({
								url : ContextPath+"/updateComments",
								data : param,
								success : function(datas) {
									var newDiv = comment.create(datas);
									var oldDiv = $("#c"+seq);
									console.log(oldDiv+"ddddddddddd");
									$("body").append($("#UpdateForm"));
									$("#UpdateForm").css("display", "none");	
									$(newDiv).replaceAll(oldDiv);
								}
							});	 			 	
						}
	}
	//댓글등록
	$("#addComment").on("click", function(){comment.add()});
	
	//댓글수정Form 보여주기
	$("#commentList").on("click",".updateComment", function(event){ console.log("event = ", event); // 발생한 이벤트 정보를가지고 있는 JQuery Event객체
																	console.log("$(event) = ", $(event)); // JQuery Event객체를 가지고 있는 JQuery 객체
																	console.log("$(event)[0] = ", $(event)[0]); // JQuery Event객체를 가지고 있는 JQuery 객체에서 JQuery Event 객체에 접근.
																	console.log("$(event).get(0) = ", $(event).get(0)); // 위와 마찬가지의 의미
																	console.log("event.currentTarget = ", event.currentTarget); // 이벤트가 발생한 태그
																	console.log("$(this) = ", $(this)); // 이벤트가 발생한 태그의 정보를 가지고 있는 JQuery 객체
																	console.log("this = ", this); // 이벤트가 발생한 태그
																	comment.updateView($(this))});
	//댓글수정
	$("#update").on("click", function(){comment.update()});
	//해당 게시글의 댓글목록 불러옴
	comment.list();
	
});
</script>
<title>게시글 상세화면 및 댓글목록</title>
</head>
<body>

<!-- 댓글 등록 폼 -->
<div>
	<form action="" id="CommentForm">
	<input type="hidden" name="boardId" value="${param.boardId}">
	<table border="1">
		<tr><td>작성자 <input type="text" name="name" size="10"></td></tr>
		<tr><td>내용<textarea name="content" rows="4" cols="20"></textarea></td></tr>
	<tr><td><input type="button" id="addComment" value="등록">
	</table>
	</form>
</div>
<!-- 댓글 등록 폼 끝 -->
<!-- 댓글 수정 -->
<div>
	<form action="" id="UpdateForm" style="display: none;">
	<input type="hidden" name="seq" value="">
	<table border="1">
		<tr><td>작성자 <input type="text" name="name" size="10"></td></tr>
		<tr><td>내용	<textarea name="content" rows="4" cols="20"> </textarea></td></tr>
	<tr><td><input type="button" id="update" value="수정"></td>
	</table>
	</form>
</div>
<!-- 댓글 수정 끝 -->
<!-- 댓글 목록 -->
<div id="commentList"></div>
</body>
</html>