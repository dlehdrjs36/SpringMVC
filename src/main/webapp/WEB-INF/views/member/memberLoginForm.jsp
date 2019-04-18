<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<style>
._btn, .input-group-addon{
	background-color: rgb(26, 188, 156) ;
	color: white; 
}
.input-group {
    margin-top: 1em;
    margin-bottom: 1em;
} 

.login-box {
    line-height: 2.3em;
    font-size: 1em;
    color: #aaa;
    margin-top: 1em;
    margin-bottom: 1em;
    padding-top: 0.5em;
    padding-bottom: 0.5em;
}

</style>
<body>
		<div class="row" style="height:800px">
			<div class="col-md-3" style="width:100%;"> 
				<div class="login-box well" style="width:600px; margin-left: auto; margin-right:auto; margin-top:100px;">
					<form accept-charset="UTF-8" role="form" method="post" action="${pageContext.servletContext.contextPath}/user/login">
						<legend>로그인</legend>
						<div class="input-group">
							<span class="input-group-addon" ><i class="fa fa-user"></i></span>
							<input type="text" id="userId" name="userId" value='' placeholder="ID을 입력하세요" class="form-control" />
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" id="password" name="password" value="" placeholder="비밀번호를 입력하세요" class="form-control" />
						</div>
						<button type="submit" id="login-submit"	class="btn btn-default btn-block  _btn">로그인</button>
						<span class='text-center'><a href="<c:url value='/'/>support/MemberFindIdForm" class="text-sm">아이디 찾기 /</a></span><span class='text-center'>
						<a href="<c:url value='/'/>support/MemberFindPasswordForm" class="text-sm"> 비밀번호 찾기</a></span>
						<div class="form-group">
							<a href="${pageContext.request.contextPath}" class="btn btn-default btn-block _btn">회원가입</a>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>