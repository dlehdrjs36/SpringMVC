<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<title>com.springmvc.test.web.fileupload 패키지- 드래그&드랍 파일 업로드</title>
</head>
<style>
#dropzone {
	border: 2px dotted #3292A2;
	width: 90%;
	height: 50px;
	color: #92AAB0;
	text-align: center;
	font-size: 24px;
	padding-top: 12px;
	margin-top: 10px;
}
</style>
<script>
<!-- 이벤트 객체의 대표적인 메소드 대표적인 메소드로 preventDefault와 stopPropagation, stopImmediatePropagation -->
//id가 dropzone인 엘리먼트에 drag 가능하게 이벤트를 붙이는 코드.
$(function () {
    var obj = $("#dropzone");
	
    //obj.on('이벤트') : 해당하는 이벤트를 #dropzone에 추가한다.
    //드래그 대상 지역에 드래그한 상태로 마우스가 올라가 있는 경우,
     
    obj.on('dragenter', function (e) {
    	 //console.log(e);
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px solid #5272A0'); //여기서 this는 이벤트의 주체가 되는 Element를 의미한다. 즉, id가 dropzone인 Element.
    });

    //드래그 대상 지역에서 드래그한 상태의 마우스가 벗어난 경우,
    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');
    });
	
    //요소나 텍스트 블록이 적합한 드롭 대상 위로 지나갈 때 발생한다.(매 수백 밀리초마다 발생한다.)
    obj.on('dragover', function (e) {
    //	 console.log("드래그 지역 Check")
         e.stopPropagation();
         e.preventDefault();
    });

    //드래그 대상 지역에 드래그한 파일들을 놓았을 경우, 수행할 콜백함수.
    //콜백함수의 매개변수 e는 #dropzone에 발생한 이벤트의 정보를 가지고 있다.(drop 이벤트) 이 객체(e)는 Jquery 이벤트 객체이다. 
    //Jquery 이벤트 객체의 originalEvent에 실제 브라우저에서 발생한 내장 이벤트 객체(네이티브 객체)를 담는다.(브라우저에서 발생한 이벤트 정보는 jquery객체.originalEvent 에 들어있다.) 
    //이는 다른 브라우저가 달라도 Jqeury 이벤트 객체를 이용해서 이벤트 정보를 얻고 사용할 수 있다.
    // https://developer.mozilla.org/ko/docs/Web/API/DataTransfer 자세한 정보 여기서 확인
    obj.on('drop', function (e) {	
   		 console.log(e);
   		 console.log(e.originalEvent);
      // console.log(e.originalEvent.dataTransfer);
   	  // console.log(e.originalEvent.dataTransfer.files);
         e.preventDefault();
 
         $(this).css('border', '2px dotted #8296C2');

         var files = e.originalEvent.dataTransfer.files;
         if(files.length < 1)
              return;

         F_FileMultiUpload(files, obj);
    });
});

// 파일 멀티 업로드
function F_FileMultiUpload(files, obj) {
     if(confirm(files.length + "개의 파일을 업로드 하시겠습니까?") ) {
         var data = new FormData();
         for (var i = 0; i < files.length; i++) {
            data.append('multiUploadFile', files[i]); // 서버에서 받는 DTO와 이름 일치시켜주어야 한다.
         }
         console.log(data);

         var url = "./ajaxFileUpload.do";
         $.ajax({
            url: url,
            method: 'post',
            data: data,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function(res) { //res는 서버로부터 응답받은 값을 가지고 있다.
            	console.log(res);
                F_FileMultiUpload_Callback(files); //비동기적으로 호출되는 콜백함수.
            }
         });
     }
}
// 파일 멀티 업로드 Callback
function F_FileMultiUpload_Callback(files) {
	 console.log(files);
     for(var i=0; i < files.length ; i++)
         console.log(files[i].name + " - 크기 :" + files[i].size + " 업로드 완료.");
}

</script>
<body>
	<div id="dropzone">Drag & Drop Files Here</div>
</body>
</html>