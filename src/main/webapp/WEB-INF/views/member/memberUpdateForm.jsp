<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정</title>
<style>
textarea.textarea1 {
	resize: none;
	width: 100%;
	height: 80px;
}    

</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!--  부트스트랩 -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<!-- <script>
	var passwordRegexCheck = false;
	var path = "<c:url value='/'/>";
	function PasswordRegexCheck() {
		var password = document.getElementById("password").value;
		// 특수문자 / 문자 / 숫자 포함 형태의 8~15자리 이내의 암호 정규식
		var passwordRegex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		if (passwordRegex.test(password)) {
			document.getElementById("PasswordRegexCheck").innerHTML = '<font color="blue">사용할수 있는 비밀번호 입니다.</font>';
			passwordRegexCheck = true;
		} else {
			document.getElementById("PasswordRegexCheck").innerHTML = '<font color="red">비밀번호는 특수문자 /문자/ 숫자를 한자 이상  포함 형태의 8~15자리 이내여야 합니다.</font>';
			passwordRegexCheck = false;
		}
	}

	function UpdateMember() {
		var userId = document.getElementById("id").value;
		var password = document.getElementById("password").value;
		var password2 = document.getElementById("password2").value;
		var name = document.getElementById("name").value;
		var email = document.getElementById("email").value;
		var tel1 = document.getElementById("tel1").value;
		var tel2 = document.getElementById("tel2").value;
		var tel3 = document.getElementById("tel3").value;
		var postcode = document.getElementById("postcode").value;
		var address = document.getElementById("address").value;
		var addrDetail = document.getElementById("location").value;
		var introduction = document.getElementById("introduction").value;

		if (password || password2) {
			if (passwordRegexCheck == false) {
				alert('비밀번호는 특수문자 /문자/ 숫자를 한자 이상  포함 형태의 8~15자리 이내여야 합니다.');
				return false;
			}
			if (password != password2) {
				alert('비밀번호가 일치하지 않습니다.');
				return false;
			}
		}

		if (!email) {
			alert('이메일주소가 입력되지 않았습니다.');
			return false;
		} else {
			var emailRegexp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
			if (!emailRegexp.test(email)) {
				alert('이메일 주소 형식은 OOO@OOO.OOO 입니다.');
				return false;
			}
		}
		if (!postcode || !address || !addrDetail) {
			alert('주소가 입력되지 않았습니다.');
			return false;
		}

		if (!introduction) {
			alert('자기소개가 입력되지 않았습니다.');
			return false;
		}

		if (!tel1 || !tel2 || !tel3) {
			alert('전화번호가 입력 되지 않았습니다.');
			return false;
		} else {
			var regexp = /[0-9]{4}/;
			if (!regexp.test(tel2) || !regexp.test(tel3)) {
				alert('전화번호는 숫자이여야 합니다.');
				return false;
			}
		}

		var check = confirm('ID : ' + userId + '\n' + '이름 : ' + name + '\n'
				+ '이메일 : ' + email + '\n' + '전화번호 : ' + tel1 + '-' + tel2 + '-'
				+ tel3 + '\n' + '우편번호 : ' + postcode + '\n' + '주소 : ' + address
				+ '\n' + '상세주소 : ' + addrDetail + '\n' + '나의 소개 : '
				+ introduction + '\n' + '상기의 정보로 회원 가입을 하시겠습니까?');
		if (check) {
			var form = document.createElement("form");
			var parm = new Array();
			var input = new Array();
			form.action = path+"forme/UpdateMember";
			form.method = "post";
			parm.push([ 'userId', userId ]);
			parm.push([ 'password', password ]);
			parm.push([ 'name', name ]);
			parm.push([ 'phoneNum', tel1 + '-' + tel2 + '-' + tel3 ]);
			parm.push([ 'email', email ]);
			parm.push([ 'postcode', postcode ]);
			parm.push([ 'address', address ]);
			parm.push([ 'addrDetail', addrDetail ]);
			parm.push([ 'introduction', introduction ]);

			for (var i = 0; i < parm.length; i++) {
				input[i] = document.createElement("input");
				input[i].setAttribute("type", "hidden");
				input[i].setAttribute('name', parm[i][0]);
				input[i].setAttribute("value", parm[i][1]);
				form.appendChild(input[i]);
			}
			document.body.appendChild(form);
			form.submit();
		}
	}
</script> -->
<body>
<div style="height:70px"></div>
	<h3>프로필 설정</h3>
	<hr>
	<!-- 아이디 -->
	<div class="form-group">
		<label style="color: lightgrey; width:150px;" class="col-lg-1 control-label">＊아이디</label>
		<div class="col-lg-4" style="padding-left: 3px;">
			<input class="form-control" id="userId" type="text" size="10"
				readonly="readonly" value="${member.id}"
				style="color: lightgrey">
		</div>
		<br>
		<br>
	</div>
	<!-- 이름 -->
	<div class="form-group">
		<label style="color: lightgrey; width:150px;" class="col-lg-1 control-label">＊이름</label>
		<div class="col-lg-4" style="padding-left: 3px;">
				<input class="form-control" id="name" type="text" size="10" readonly="readonly"
					value="${member.name}" style="color: lightgrey">
		</div>
		<br>
		<br>
	</div>
	<!-- 새 비밀번호 -->   
	<div class="form-group">
		<label class="col-lg-1 control-label" style="width:150px;">＊새 비밀번호</label>
		<div class="col-lg-6" style="padding-left: 3px;">
			<input class="form-control" id="password" type="password" size="10"	maxlength="15" onkeyup="PasswordRegexCheck()">
			<span id="PasswordRegexCheck"><font color="red">비밀번호는 특수문자/문자/ 숫자를 한자 이상 포함 형태의 8~15자리 이내여야 합니다.</font></span>
		</div>
		<br>
		<br>
		<br>
	</div>
	<!-- 비밀번호 확인 -->
		<div class="form-group">
		<label class="col-lg-1 control-label" style="width:150px;">＊비밀번호 확인</label>
		<div class="col-lg-6" style="padding-left: 3px;">
			<input class="form-control" id="password2" type="password" size="10">
		</div>
		<br>
		<br>
	</div>
	<!-- 이메일 주소 -->
	<div class="form-group">
		<label style="color: lightgrey; width:150px;" class="col-lg-1 control-label">＊E-mail 주소</label>
		<div class="col-lg-4" style="padding-left: 3px;">
				<input class="form-control" id="email" type="email" size="20"
					value="${member.email}" readonly="readonly"
					style="color: lightgrey">
		</div>
		<br>
		<br>
	</div>
	<!-- 전화번호 -->
	<div class="form-group">   
		<label style="width:150px;" class="col-lg-1 control-label">＊전화번호</label>
		<div class="col-lg-2" style="padding-left: 3px;">
				<select class="form-control" id="tel1" style="width:150px;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select> 							
		</div>
		<div class="col-lg-1" style="display: table; width: 1px; height: 34px; padding-left: 0px; padding-right: 0px;">
			<span style="display: table-cell; vertical-align: middle;">-</span>
		</div>
		<div class="col-lg-2">
		<input class="form-control" type="text" id="tel2" style="width:150px;" size="10" maxlength="4"> 
		</div>
		<div class="col-lg-1" style="display: table; width: 1px; height: 34px; padding-left: 0px; padding-right: 0px;">
			<span style="display: table-cell; vertical-align: middle;">-</span>
		</div>
		<div class="col-lg-2">
		<input class="form-control" type="text" id="tel3" style="width:150px;" size="10" maxlength="4">
		</div>
		<br>
		<br>
	</div>
	<script>
				var phoneNum = '${member.phone}';
				var telArray = phoneNum.split('-');
				document.getElementById("tel1").value = telArray[0];
				document.getElementById("tel2").value = telArray[1];
				document.getElementById("tel3").value = telArray[2];
	</script>
 
	<!-- 주소 -->
	<%-- 	<div class="form-group">
		<label class="col-lg-1 control-label" style="width:150px;">＊주소</label>
				<div class="col-lg-3" style="padding-left: 3px;">
					<input class="form-control" type="text" size="10" id="postcode" id="postcode"
					value="${member.address}" readonly="readonly">
				</div>
				<div class="col-lg-2" style="padding-left: 0px;">
					<input type="button" onclick="SearchPostcode()" value="우편번호검색" class="form-control">
			
				</div>
				<div id="layer"
						style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
						<img
							src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
							id="btnCloseLayer"
							style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
							onclick="closeDaumPostcode()" alt="닫기 버튼">
					</div> <script src="../js/SearchAddr.js"></script>
				<br>
			</div>  
				<div class="form-group">
				<label class="col-lg-1 control-label" style="width:150px;"></label>
				<div class="col-lg-5" style="padding-left: 3px;">
					<input class="form-control" type="text"
					id="address" size="60" id="address" value="${member.location}"
					readonly="readonly">
				</div>
				<br>
			</div> --%>
<%-- 			<div class="form-group">
				<label class="col-lg-1 control-label" style="width:150px;"></label>
				<div class="col-lg-5" style="padding-left: 3px;">
					<input class="form-control" type="text" size="60"
					id="addrDetail" value="${member.addrDetail}">
				</div>
				<br>
				<br> 
			</div>   --%>
		<!-- 자기소개 -->
<%-- 		<div class="form-group">
		<label class="col-lg-1 control-label" style="width:150px;">자기소개</label>
		<div class="col-lg-6" style="padding-left: 3px;">
			<textarea class="form-control textarea1" id="introduction">${member.introduction}</textarea>
		</div>
		<br>
		<br>
		<br>
		</div> --%>
		<!-- 수정버튼 -->
			<div class="form-group">
			<label class="col-lg-1 control-label" style="width:150px;"></label>
				<div class="col-lg-offset-2 col-lg-4" style="text-align: right;">
					<input class="btn btn-default" type="button" onclick="UpdateMember()" value="정보수정하기">	
					<input class="btn btn-default" type="button" onclick="location.href='<c:url value='/'/>/forme/MemberDeleteConfirmForm'" value="회원탈퇴하기">	
					<input class="btn btn-default" type="button" value="취소">	
				</div>
			</div>
</body>
</html>