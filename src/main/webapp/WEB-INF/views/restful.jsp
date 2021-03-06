<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>RESTful 웹서비스 클라이언트(JSON)</title>
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" >
	$(function(){
		userList();

		userSelect();
		
		userDelete();
		
		userInsert();
	
		userUpdate();
		
		init();
	});
	
	//초기화
	function init() {
		//초기화 버튼 클릭
		$('#btnInit').on('click',function(){
			$('#form1').each(function(){
				this.reset();
			});
		});
	}//init
	
	//사용자 삭제 요청
	function userDelete() {
		//삭제 버튼 클릭, 이벤트를 부모태그에 위임. 
		//id btnDelete라는 태그가 추가될 때, 새로 이벤트를 추가안해도 이벤트처리가 적용됨.
		
		$('body').on('click','#btnDelete',function(){
			var userId = $(this).closest('tr').find('#hidden_userId').val();
			var result = confirm(userId +" 사용자를 정말로 삭제하시겠습니까?");
			if(result) {
				$.ajax({
					url:'users/'+userId,  type:'DELETE',
					contentType:'application/json;charset=utf-8',
					dataType:'json',
					error:function(xhr,status,msg){
						console.log("상태값 :", status + " Http에러메시지 :",msg);
					}, success:function(xhr) {
						console.log(xhr.result);
						userList();
					}
				});      }//if
		}); //삭제 버튼 클릭
	}//userDelete
	
	//사용자 조회 요청
	function userSelect() {
		//조회 버튼 클릭
		$('body').on('click','#btnSelect',function(){
			var userId = $(this).closest('tr').find('#hidden_userId').val();
			//특정 사용자 조회
			$.ajax({
				url:'users/'+userId,
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				error:function(xhr,status,msg){
					alert("상태값 :" + status + " Http에러메시지 :"+msg);
				},
				success:userSelectResult
			});
		}); //조회 버튼 클릭
	}//userSelect
	
	//사용자 조회 응답
	function userSelectResult(xhr) {
		var user = xhr.data;
		$('input:text[name="userId"]').val(user.id);
		$('input:text[name="name"]').val(user.name);
		$('input:radio[name="gender"][value="'+user.gender+'"]').prop('checked', true);
		$('select[name="city"]').val(user.location).attr("selected", "selected");
	}//userSelectResult
	
	//사용자 수정 요청
	function userUpdate() {
		//수정 버튼 클릭
		$('#btnUpdate').on('click',function(){
			var userId = $('input:text[name="userId"]').val();
			var name = $('input:text[name="name"]').val();
			var gender = $('input:radio[name="gender"]:checked').val();
			var city = $('select[name="city"]').val();	
			$.ajax({ 
			    url: "users", 
			    type: 'PUT', 
			    dataType: 'json', 
			    data: JSON.stringify({ id: userId, name:name, gender: gender, location: city }),
			    contentType: 'application/json',
			    mimeType: 'application/json',
			    success: function(data) { 
			    	$('#btnInit').click(); //수정 완료되면 해당 필드는 초기화.
			    	alert(data.id+"정보가 수정 완료되었습니다.");
			        userList();
			    },
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    }
			});
		});//수정 버튼 클릭
	}//userUpdate
	
	//사용자 등록 요청
	function userInsert(){
		//등록 버튼 클릭
		$('#btnInsert').on('click',function(){
			var userId = $('input:text[name="userId"]').val();
			var name = $('input:text[name="name"]').val();
			var gender = $('input:radio[name="gender"]:checked').val();
			var city = $('select[name="city"]').val();		
			$.ajax({ 
			    url: "users",  
			    type: 'POST',  
			    dataType: 'json', 
			    data: JSON.stringify({ id: userId, name:name, gender: gender, location: city }),
			    contentType: 'application/json', 
			    mimeType: 'application/json',
			    success: function(response) {
			    	if(response.result == true) {
			    		$('#btnInit').click(); //등록완료되면 해당 필드는 초기화.
			    		alert( userId +"회원 등록완료.");
			    		userList();
			    	}
			    }, 
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    } 
			 });  
		});//등록 버튼 클릭
	}//userInsert
	
	//사용자 목록 조회 요청
	function userList() {
		$.ajax({
			url:'users',
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			error:function(xhr,status,msg){
				alert("상태값 :" + status + " Http에러메시지 :"+msg);
			},
			success:userListResult
		});
	}//userList
	
	//사용자 목록 조회 응답
	function userListResult(xhr) {
		console.log(xhr);
		console.log(xhr.data);
		$("tbody").empty();
		$.each(xhr.data,function(idx,item){
			$('<tr>')
			.append($('<td>').html(item.id))
			.append($('<td>').html(item.name))
			.append($('<td>').html(item.gender))
			.append($('<td>').html(item.location))
			.append($('<td>').html('<button id=\'btnSelect\'>조회</button>'))
			.append($('<td>').html('<button id=\'btnDelete\'>삭제</button>'))
			.append($('<input type=\'hidden\' id=\'hidden_userId\'>').val(item.id))
			.appendTo('tbody');
		});//each
	}//userListResult
</script>
</head>
<body>
<h1> 확인하기 </h1>
<p>"pageContext.request.requestURL" : ${pageContext.request.requestURL}</p>
<p>"pageContext.request.requestURI" : ${pageContext.request.requestURI}</p>
<p>"pageContext.request.contextPath" : ${pageContext.request.contextPath}</p>
<h2> Rest API</h2>
<h3><a href="<c:url value="/users.html"/>" onclick="window.open(this.href,'_blank','width=800,height=600, location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes');return false;">HTML : <c:url value="/users.html"/></a><br></h3>
<h3><a href="<c:url value="/users.json"/>" onclick="window.open(this.href,'_blank','width=800,height=600, location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes');return false;">JSON : <c:url value="/users.json"/></a><br></h3>
<h3><a href="<c:url value="/users.xml"/>" onclick="window.open(this.href,'_blank','width=800,height=600, location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, scrollbars=yes');return false;">XML : <c:url value="/users.xml"/></a><br></h3>
<hr>

<div class="container">
	<form id="form1"  class="form-horizontal">
		<h2>사용자 등록 및 수정</h2>
		<div class="form-group">		
			<label >아이디:</label>
			<input type="text"  class="form-control" name="userId" >
		</div>	
		<div class="form-group">
			<label>이름:</label>
			<input type="text"  class="form-control"  name="name" >
		</div>	
		<div class="form-group">
			<label >성별:</label>
			<div class="radio">
				<label><input type="radio"  name="gender"  value="남">남</label>
			</div>
			<div class="radio">
				<label><input type="radio"  name="gender"  value="여">여</label>
			</div>	
		</div>	    
		<div class="form-group">   
			<label>거주지:</label>
				<select class="form-control" name="city">
					   		<option value="서울">서울</option>
					   		<option value="경기">경기</option>
					   		<option value="부산">부산</option>
					   		<option value="대구">대구</option>
					   		<option value="제주">제주</option>
				</select>
		</div>  
		<div class="btn-group">      
				<input type="button"  class="btn btn-primary" value="등록"  id="btnInsert" /> 
				<input type="button"  class="btn btn-primary" value="수정"  id="btnUpdate" />
				<input type="button"  class="btn btn-primary" value="초기화" id="btnInit" />
		</div>
	</form>
</div>		
<hr/>		
<div class="container">	
	<h2>사용자 목록</h2>
	<table class="table text-center">
		<thead>
		<tr>
			<th class="text-center">아이디</th>
			<th class="text-center">이름</th>
			<th class="text-center">성별</th>
			<th class="text-center">거주지</th>
		</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>	
</body>
</html>