<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- AjaxForm -->
<script src="http://malsup.github.com/jquery.form.js"></script>

<title>com.springmvc.test.web.fileupload 패키지- 파일 업로드 및 main 화면</title>
<script>
	$(document.body).ready( function() { $("#insertBtn").on("click", function() {
											$("#frm").ajaxForm(
															{
																dataType : "json",
																url : './ajaxFileUpload.do',
																success : function(
																		result) {
																	if (result != null) {
																		alert("등록되었습니다.");
																		console.log(result);
																		// ',' 기준으로 토큰을 잘라서 배열에 담아서 반환. token은 잘린 토큰들을 가지고있는 배열을 가지고 있다.
																		var token = result[result.length - 1].boardFile.split(",");
																		console.log(token);
																		var fileInfo = "";
																		for ( var info in token) {
																			fileInfo += "<a href='./FileDown.do?atchFileId="
																					+ token[info]
																					+ "'>"
																					+ token[info]
																					+ "</a> ";
																		}
																		var frame = "<tr><td>"
																				+ fileInfo
																				+ "</td></tr>";
																		$("#info").append(frame);
																		$("#frm")[0].reset();
																	}
																},
																error : function(
																		result) {
																	console.log(result)
																	alert("파일을 등록해주십시오.");
																	return;
																}
															}).submit();
										});
					});
</script>


</head>
<body>


	<!--단일 파일 업로드 -->
	<form action="<c:url value= "/fileUpload.do" />" method="post"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table width="50%" border="1" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" bgcolor="orange"><b>단일 파일업로드 화면입니다.</b></td>
			</tr>
			<tr bgcolor="lightgray">
				<td align="center"><br> <br> <br> <br> <input
					type="file" name="uploadFile" /> <input type="submit" /> <br>
					<br> <br> <br></td>
			</tr>
			<!-- 	
		<input type="text" name="id" id="id" /> 
		<input type="text" name="ctnt" id="ctnt" /> 
		<input type="text" name="writer" id="writer" /> 
		<input type="file" name="atch" />  
-->
		</table>
	</form>
	<!--다중 파일 업로드 -->
	<div style="width: 100%; height: 260px; border: 1px solid red;">
		<div
			style="width: 50%; height: 100%; border: 1px solid blue; float: left;">
			<form id="frm" enctype="multipart/form-data" method="post">
				<table id="ft" width="100%" border="1" cellspacing="0"
					cellpadding="0">
					<tr>
						<td align="center" bgcolor="orange"><b>다중 파일업로드 화면입니다.</b></td>
					</tr>
					<tr bgcolor="lightgray">
						<td align="center"><br> <br> <br> <br> <input
							type="file" name="multiUploadFile" /> <input type="file"
							name="multiUploadFile" /> <br> <br> <br> <br>
						</td>
					</tr>
					<tr>
						<td><a href="javascript:;" id="insertBtn">저장</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id=info
			style="width: 48%; height: 100%; border: 1px solid black; float: left;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" bgcolor="orange"><b>업로드한 파일목록.</b></td>
				</tr>
			</table>
		</div>
	</div>
	<div>
		<ul>
			<li><a href="./broadcast.do">com.springmvc.test.web.websocket
					패키지- 채팅화면</a></li>
			<li><a href="./getMemberList.do">com.springmvc.test.web.paging
					패키지- 회원목록화면-페이징</a></li>
			<li><a href="./getJsonMemberList.do">com.springmvc.test.web.jackson
					패키지- 자바객체-JSON 형변환 </a></li>
			<li><a href="./getJsonMemberList2.do">com.springmvc.test.web.jackson
					패키지- 자바객체-XML 형변환 </a></li>
			<li><a href="./Arithmetic.do">com.springmvc.test.web.exception
					패키지- 수학에러발생</a> <a href="./NullPointer.do">com.springmvc.test.web.exception
					패키지- 널포인터에러발생</a> <a href="./ArrayLangth.do">com.springmvc.test.web.exception
					패키지- 배열길이에러발생</a></li>
			<li><a href="./localeView.do">src/main/resources/message 폴더-
					다국어 처리</a></li>
			<li><a href="./fileUploadView2.do">com.springmvc.test.web.fileupload
					패키지 - Drag&Drop 파일업로드</a></li>
			<li><a href="./report.do"> com.springmvc.test.web.ireport
					패키지 - iReport(pdf 보고서 틀 생성)</a></li>
			<li><a href="./reportChart.do"> com.springmvc.test.web.ireport
					패키지 - iReport Chart</a></li>
			<li><a href="./excelCreate.do"> com.springmvc.test.web.excel
					패키지 - xlsExcel(엑셀다운(지정폴더))</a></li>	
			<li><a href="./xlsxExcelView.do"> com.springmvc.test.web.excel
					패키지 - xlsxExcel(엑셀다운(브라우저 기본설정폴더))</a></li>
			<li><a href="./commonemail.do"> com.springmvc.test.web.email
					패키지 - commonemail(메일전송)</a></li>		
			<li><a href="./javaxemail.do"> com.springmvc.test.web.email
					패키지 - javaxemail(메일전송)</a></li>	
			<li><a href="./validationView.do"> com.springmvc.test.web.validation
					패키지 - validation(서버측에서 유효성검사)</a></li>	
			<li><a href="./getBoardList.do"> com.springmvc.test.web.comments
					패키지 - 댓글기능 - 게시글 조회</a></li>
			<li><a href="./users"> com.springmvc.test.web.rest
					패키지 - rest방식의 crud 수행(Rest Api)</a></li>			
		</ul>
	</div>
</body>
</html>