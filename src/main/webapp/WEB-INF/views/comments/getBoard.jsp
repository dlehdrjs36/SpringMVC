<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>2018. 11. 6.</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
$(function(){ 
	var context = '${pageContext.request.contextPath}'; // 절대경로로 접근할때 사용. ./로 하면 상대경로
	console.log(context);
	
	var name = {
			a : 'a입니다',
			b : '21'
	};
	console.log(name);
	name.c = "c입니다";
	
	// 등록한 댓글을 나타내는 폼을 만드는 function.
	function makeCommentView(comment){   //comment는 서버로부터 받은 CommentsVO 객체이다.
		var div = $("<div>");  			 //$(element)는 무조건 배열 타입(구조) 이다. , document.createElement()와 같은개념. 태그를 생성함.$("태그")
		div.attr("id", "c"+comment.seq); // <div id="c1"></div> div에 접근하기위해서 id설정
		div.addClass('comment'); 		 // <div id="c1" class="comment"></div>
		
//div[0].comment의 comment는 클래스이름인 comment를 의미하는것이 아니다. - 중요
//DOM에서 div 객체의 속성으로 comment라는것을 추가하고 그 속성의 값으로 서버에서 전달받은 값을 셋팅하는 것이다.
		div[0].comment= comment;  		 //{id:1, title:'', content:'', .... }
		

		//<div id="c1" class="comment"></div> 에 comment필드가 생성되고 comment필드의 내용은 {id:1, title:'', content:'', .... }
		// == <div id="c1" class="comment" comment={id:1, title:'', content:'', .... } > 정확한 표현은 아님. 이해하려고 적어놓은것임	
		//수정,삭제 부분은 로그인했을때 해당하는 사람만 추가되도록 수정.
		var str ="<strong class='commentName'> 이름 : " + comment.name + "</strong> <br>" 
		        +"<span class='commentContent'> 내용 : " + comment.content +"</span> <br>"
				+"<button type=\"button\" class=\"btnUpdFrm\">수정</button>"
				+"<button type=\"button\" class=\"btnDel\">삭제</button>"
		div.html(str); //== <div> <strong> </strong> <button> ... ... </div>
		return div;
	}
	
	//댓글 등록 이벤트-이해o
	$("#btnAdd").click(function(){
		$("#btnCancel").click(); // 입력되어있는 값들을 초기화 시킴.
		var params = $("[name=addForm]").serialize(); // form에있는 모든 필드값들을 하나로 묶어줌(form의 모든필드를  쿼리스트링으로 변환. 만들어줌.)
		var url = context + "/insertComments";
		$.getJSON(url, params, function(datas){
			$("#commentList").append( makeCommentView(datas) );
			$("[name=addForm]")[0].reset(); // reset은 clear와 같음, 댓글 등록 폼을 clear시킴.
		});
	});
	/*** 입력한 값을 Ajax로 댓글 등록, 서버에서 댓글 등록처리가 완료되면 등록된 댓글을 보여줄 영역에 표시	***/
	
	
	//댓글 수정 이벤트
	$("#btnUpd").click(function(){
		var params = $("[name=updateForm]").serialize(); // form의 모든필드를 쿼리문자열로 변환. do?name=""&age="".....
		var url = context + "/updateComments";
		$.getJSON(url, params, function(datas){
			var newDiv = makeCommentView(datas);
			var oldDiv = $("#c"+datas.seq);
			$("#btnCancel").click();
			$(newDiv).replaceAll(oldDiv);  // 수정된 DIV를 교체
		});
	});
	//댓글 수정 이벤트(수정할 댓글밑에 수정폼 보이게 함)
	//commentList의 class=btnUpdFrm을 클릭했을 때 발생.
	$("#commentList").on("click", ".btnUpdFrm", function(){
		//this는 클릭이벤트가 발생한 태그(<button>). 
		console.log("댓글 수정 이벤트 발생")
		console.log("this = " + $(this));
		console.log("this.parent = " + $(this).parent());
		var seq = $(this).parent().attr("id").substring(1);
		
		var comment = $(this).parent().get(0).comment; // comment에 있는 값을 읽어내면 값을 다시 찾을 필요없음
		//-- $("#updateForm [name=name]").val($("#c"+seq+">.commentName").text());
		//-- $("#updateForm [name=content]").val($("#c"+seq+">.commentContent").text());
		
/* 		console.log("seq = " + seq);
		console.log("$(this).parent().get(0) = " + $(this).parent().get(0));
		console.log("comment = " + comment);
*/
		//수정할 데이터 입력필드에 셋팅
		$("#updateForm [name=seq]").val(seq);    
		$("#updateForm [name=name]").val(comment.name);  // == $("#updateForm [name=name]").val($("#c"+seq+">.commentName").text()); //부모태그의 자식에 접근. 부모태그를 구분하는 id가 있기때문에 부모에 해당하는 자식에 접근가능
		$("#updateForm [name=content]").val(comment.content);  // == $("#updateForm [name=content]").val($("#c"+seq+">.commentContent").text());

		//댓글수정폼을 수정할 댓글 밑으로 이동시키고 보이게한다
		$("#c"+seq).append($('#commentUpdate'));  
		$('#commentUpdate').show();   
	});
	
	//수정 취소 이벤트
	$("#btnCancel").click(function(){
		$("[name=updateForm]")[0].reset();   //폼 필드 클리어 [0] document로 접근하기위해서 사용.
		$("#comments").append( $("#commentUpdate") );//수정 폼(div)를 이동
		$("#commentUpdate").hide();    // 수정폼 숨기기
	});
	//댓글 삭제 이벤트. 부모태그에 이벤트 위임. 새로추가되는 태그들도 부모태그의 이벤트를 따라간다. 이벤트는 body가 그려질때 부착됨. body가 완료된후에는 새로 추가해주어야함. 하지만 위임을 사용하면 이벤트의 추가없이 새로추가되는 자식태그에 이벤트 적용가능.
	$("#commentList").on("click", ".btnDel", function(){
		var seq = $(this).parent().attr("id").substr(1); //클릭한 삭제버튼의 부모(div)에 id에서 번호만 잘라서 가져옴. 
		// 버튼을 클릭한 태그 $(this) (자식)의 부모태그 id에서 번호만 잘라서 seq에 저장 
		if(confirm("삭제할까요?")) {
			var params = "seq="+ seq;  // { seq : seq };
			var url = context + "/deleteComments";
			$.getJSON(url, params, function(datas){
				$('#c'+datas.seq).remove();
			});
		}
	});
	
	//댓글 목록조회 요청, 해당하는 게시글의 댓글목록 정보를 보내준다.
	function loadCommentList() {
		var params = { boardId : '${board.boardId}' };
		$.getJSON(context + "/getCommentsList", params, function(datas){
			for(i=0; i<datas.length;i++) {
				$("#commentList").append( makeCommentView(datas[i]) );
				
			}
			console.log(name.a);
			console.log(name.c);
		});
	
	}
	loadCommentList(); //댓글목록 요청 실행
});
</script> 
</head>
<body>
<h3> 게시글 단건조회</h3>
제목 : ${board.boardTitle} <br>
작성자 : ${board.boardWriter} <br>
내용 : ${board.boardContent} <br>
작성일자 : ${board.boardDate} <br>
조회수 : ${board.boardHit} <br><br>
파일 <br>
<c:forTokens var="path" items="${board.boardFile}" delims=",">
	<a href="./FileDown.do?atchFileId=${path}">${path}</a><br>
</c:forTokens>
<br>
<a href="./getBoardList.do">목록으로</a>
<br><br>
<hr>
<h4>댓글등록</h4>
<div id="comments" style="border:1px solid blue">
<!-- 등록된 댓글을 보여줄 영역 (CommentList)-->
	<div id="commentList"></div>
<!--  등록된 댓글을 보여줄 영역 끝 -->

<!-- 댓글등록 폼 -->
	<div id="commentAdd">
		<form name="addForm" id="addForm">
			<input type="hidden" name="boardId" value="${board.boardId}">
			이름: <input type="text" name="name" size="10"><br/>
			내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
			<input type="button" value="등록" id="btnAdd"/>
		</form>
	</div>
<!-- 댓글등록끝 -->

<!-- 댓글수정폼시작 -->
	<div id="commentUpdate" style="display:none">
		<form action="" name="updateForm" id="updateForm">
			<input type="hidden" name="boardId" value="${board.boardId}">
			<input type="hidden" name="seq" value=""/>
			이름: <input type="text" name="name" size="10"><br/>
			내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
			<input type="button" value="수정" id="btnUpd"/>
			<input type="button" value="취소" id="btnCancel"/>
		</form>
	</div>
<!-- 댓글수정폼끝 -->

</div>
</body>
</html>