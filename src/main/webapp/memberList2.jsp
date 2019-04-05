<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>함수 선언과 함수 표현식의 차이 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function test(str1) {
		var fm = document.rsForm;
		var name = str1.options[str1.options.selectedIndex]
				.getAttribute("name");
		fm.i_code.value = name;
	}

	function hello(name) {
		var _name = name;
		return function() {
					for (i = 0; i < 10; i++) {
							setTimeout(function() {
								console.log('Hello, ' + _name );
							}, 100);
					}		
				}
	}

	var hello1 = hello('aa');
	//	var hello2 = hello('bb');
	//	var hello3 = hello('cc');

	hello1(); // 'Hello, aa'
	//	hello2(); // 'Hello, bb'
	//	hello3(); // 'Hello, cc
	//	hello1._name = "dddd";
	//	hello1(); // 'Hello, aa'

	console.log("----------------------------");
	foo(); // success!
	function foo() {
		alert('foo');
	}
	foo(); // "foo" is not defined.
	var foo = function() {
		alert('foo');
	};
	alert(foo); // "foo" is not defined.
	(function foo() {});
	alert(foo); // "foo" is not defined.
	
	<!-- 쿠키 -->
	<!-- 쿠키 읽기 -->
	function GetCookie (name) {
		var str = name+"=";
		var pairs = document.cookie.split(";"); // 쿠키문자열을 ;을 경계로 분할
		for(var i=0; i<pairs.length; i++) {
			var pair = pairs[i].trim(); // 쿠키 앞뒤의 빈칸 제거
			var unit = pair.split("=");
			if(unit[0] == name)
				return unescape(unit[1]);
		}
		return null;
	}
	<!-- 쿠키 쓰기(기존 쿠키에 연결해서 추가) -->
	function SetCookie (name, value, expireDate) {
		var cookieStr = name + "=" + escape(value) + 
	((expireDate == null) ? "" : ("; expires=" + expireDate.toGMTString()));
	console.log(document.cookie);
	document.cookie = cookieStr;
	console.log(cookieStr, "쿠키 연결");
	console.log(document.cookie, "연결된 쿠키");
	}
	console.log(document.cookie, "쿠키");
</script>
</head>
<body>
<script>
var username = GetCookie("username");
var count = GetCookie("count");
var expire = new Date (); // 현재 시간
if (username == null) {
	count = 0;
	username = prompt("이름을 입력해 주십시오.","");
	if (username == null) {
		alert("이름을 입력하시면 보다 나은 서비스를 제공받을 수 있습니다.");
		username = "아무개";
	} else {
		expire.setTime(expire.getTime() + (365 * 24 * 3600 * 1000)); // 1년후 
		SetCookie("username",username,expire);
	}
}
count++;
expire.setTime(expire.getTime() + (365 * 24 * 3600 * 1000)); // 쿠키 유효기간 설정, 1년후 
SetCookie("count",count,expire);
document.write('<p>어서오십시오. '+username+'님의 '+count+'번째 방문을 환영합니다!');
</script>

	<form name="rsForm" method="post">
		<select name="week_val" title="~을 선택해주세요"
			onChange="javascritp:test(this);">
			<option value="">선택</option>
			<option value="value1" name="name1">1번</option>
			<option value="value2" name="name2">2번</option>
			<option value="value3" name="name3">3번</option>
			<option value="value4" name="name4">4번</option>
		</select> <input type="text" name="i_code" value="" />
	</form>
</body>
</html>

