<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring websockets</title>
</head>
<body>
	<fieldset>
		<textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
		<!-- 대화창 -->
		<br /> <input id="inputMessage" type="text" />
		<!-- 입력창 -->
		<input type="button" value="조회" onclick="send()" /> <input
			type="button" value="전체조회" onclick="sendall()" />
	</fieldset>
	<a href="./fileUploadView.do">메인화면 - 파일업로드</a>
</body>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket(
			'ws://ldgproject.tk/springserver.do'); //port, context root 각자에맞게 변경필요
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	function onMessage(event) {
		//textarea.value += "상대 : " + event.data + "\n";
		
		var msgvo = JSON.parse(event.data); // 서버에서 넘어오는값을 JSON객체로 변환시킴. event는 서버에서 넘어온 값을 의미함.
		console.log(msgvo.msg);
		
		if (msgvo.cmd =="get") {
			textarea.value += "요청한사람 (" + msgvo.msg.id +"):" + msgvo.msg.name + ", " + msgvo.msg.role +"\n";	
		} else if(msgvo.cmd == "all"){
			for (i = 0; i < msgvo.msg.length; i++) {
				textarea.value += msgvo.msg[i].name + "," + msgvo.msg[i].role +"\n";
			}		
		}
		//javascript의 foreach문 . cmd가 all인 경우
/* 		 for ( user in msgvo.msg) {
			//넘어오는것은 인덱스.
			console.log(msgvo.msg[user]);
		}  */
	
		chatAreaScroll();
	}
	function onOpen(event) {
		textarea.value += "연결 성공\n";
	}
	function onError(event) {
		console.log(event);
		alert(event.data);
	}
	function send() {
		textarea.value += "나 : (단건조회) 검색 ID : " + inputMessage.value + "\n";

		// 서버로 전송할 데이터를 담을 msg 객체 생성. MsgVO에 있는 필드명과 같게 만들어야함.
		var v_msg = {
			cmd : "get", // 명령어
			msg : document.getElementById("inputMessage").value, //검색조건
			id : "홍길동" // id: "로그인계정", 로그인 되어있다면 ${sessionScope.login}으로 받아올수있음. 세션로그인id
		};
		if ( v_msg.msg == null || v_msg.msg =="") {
			textarea.value += "검색할 회원의 아이디를 입력하십시오.\n";
		}
		else { 
			webSocket.send(JSON.stringify(v_msg)); // json구조로 바꾸어서 서버에 값 전달.
		}
		inputMessage.value = "";
	}
	function sendall() {
		textarea.value += "나 : (전체조회) " + inputMessage.value + "\n";
		// 서버로 전송할 데이터를 담을 msg 객체 생성. MsgVO에 있는 필드명과 같게 만들어야함.
		var v_msg = {
			cmd : "all", // 명령어
			msg : document.getElementById("inputMessage").value, //검색조건
			id : "홍길동2" // id: "로그인계정", 로그인 되어있다면 ${sessionScope.login}으로 받아올수있음. 세션로그인id
		};
		webSocket.send(JSON.stringify(v_msg)); // json구조로 바꾸어서 서버에 값 전달.
		inputMessage.value = "";
	}

	function chatAreaScroll() {
		//using jquery
		//var textArea = $('#messageWindow');
		//textArea.scrollTop(textArea[0].scrollHeight - textArea.height());
		//textArea.scrollTop(textArea[0].scrollHeight);
		
		//using javascript
		var textarea = document.getElementById('messageWindow');
		textarea.scrollTop = textarea.scrollHeight;
	}
</script>
</html>