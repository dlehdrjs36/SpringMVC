<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세션 스토리지에 쓰기/읽기</title>
<style>
#goods>.good { display : inline-block;  width : 300px; height : 300px; border : 1px solid blue;}
#aside { position :fixed; right:0px; bottom:300px; border : 1px solid red;}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<h3>세션 스토리지에 구입 리스트 저장/검색</h3>
<hr>
품목명 : <input id="item" type="text">
개수 : <input id="count" type="text">
<button id="save" onclick="store()">저장</button>
<button id="retrieve" onclick="retrieve()">검색</button>
<script>
var item = document.getElementById("item");
var count = document.getElementById("count");

function store() { // e는 이벤트 객체
	if(!window.sessionStorage) {
		alert("세션 스토리지를 지원하지 않습니다.");
		return;
	}
	//세션 스토리지에 (key, value) 형태로 값을 저장한다.
	sessionStorage.setItem(item.value, count.value);
}
function retrieve() { // e는 이벤트 객체
	if(!window.sessionStorage) {
		alert("세션 스토리지를 지원하지 않습니다.");
		return;
	}
	//key값으로 세션 스토리지에 들어있는 값을 구한다.
	var val = sessionStorage.getItem(item.value);
	if(val == null)
		alert(item.value + "는 구입 리스트에  없습니다.");
	else
		count.value = val;
}
</script>


</body>
</html>