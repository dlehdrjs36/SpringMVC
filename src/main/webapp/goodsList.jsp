<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로컬스토리지 이용한 장바구니 구현</title>
<style>
#goods>.good { display : inline-block;  width : 300px; height : 300px; border : 1px solid blue;}
#aside { position :fixed; right:0px; bottom:300px; border : 1px solid red;}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
var goodsList;

/* 페이지 렌더링이 완료되면 수행되는 함수  */
$(function(){
	init();      /* LocalStorage 정보를 얻어온다. (장바구니 정보) */
	viewGoods(); /* 상품을 클릭하면 LocalStorage(장바구니)에 저장되는 물품. */
});

function init(){
	goodsList = localStorage.getItem("lastViewGoodsList");
	if(goodsList == null) {
		goodsList = [];
	} else {
		goodsList = JSON.parse(goodsList); // JSON형태의 LocalStorage의 상품을  JavaScript 값이나 객체로 변환한다.
	}
}
function viewGoods() {
	//good 클래스에 클릭이벤트가 발생하면, 해당하는 클래스의 span 태그의 텍스트 값을 찾는다.
	$(".good").click(function(){
		var name = $(this).find("span").text();
		// 장바구니(LocalStorage)에 담긴 내용물이 5개이상 이라면, 오래된 것을 지움.
		if(goodsList.length >= 5) {
			goodsList.pop();
		}
		// 상품을 추가 
		goodsList.unshift({id:1,name:name, src:'#'});
		localStorage.setItem("lastViewGoodsList",JSON.stringify(goodsList));  //Storage에는 String만 추가가능. ( 키값, 벨류)
		console.log(goodsList);
		viewRender();
	});
}
// 장바구니(LocalStorage) 상황을 html 컴포넌트로 표시.
function viewRender(){
	$("ul#viewGoods").empty(); // 상품목록 초기화
	for(i in goodsList){  // 장바구니에서 하나씩 상품을 꺼냄.
			if(!isNull(goodsList[i])){ // 장바구니에 들어있는 상품이 있을때, 그 상품을 보여준다.
				$("ul#viewGoods").append(
					$("<li>").append(
						$("<a>").attr("href","/item/itemView.do?goodsId="+goodsList[i].id)
								.append(goodsList[i].name)
				        		.append($("<img>").attr("src",goodsList[i].src)
				               					  .attr("alt",goodsList[i].name)
						)
					)
				);
		   }
		  }
}

//데이터가 비어있는지 체크하는 function
function isNull(obj){
	 if(obj == '' || obj == null || obj == undefined || obj == NaN){ 
	  return true;
	 }else{
	  return false;
	 }
}
</script>
</head>
<body>
<div id="goods">
	<div class="good">
		<span>상품1</span>
	</div>
	<div class="good">
		<span>상품2</span>
	</div>
	<div class="good">
		<span>상품3</span>
	</div>
	<div class="good">
		<span>상품4</span>
	</div>
	<div class="good">
		<span>상품5</span>
	</div>
	<div class="good">
		<span>상품6</span>
	</div>
	<div class="good">
		<span>상품7</span>
	</div>
	<div class="good">
		<span>상품8</span>
	</div>	
	<div class="good">
		<span>상품9</span>
	</div>
	<div class="good">
		<span>상품10</span>
	</div>
	<div class="good">
		<span>상품11</span>
	</div>
	<div class="good">
		<span>상품12</span>
	</div>
	<div class="good">
		<span>상품13</span>
	</div>
	<div class="good">
		<span>상품14</span>
	</div>
	<div class="good">
		<span>상품15</span>
	</div>
	<div class="good">
		<span>상품16</span>
	</div>		
</div>
<div id="aside">
<ul id="viewGoods"></ul>
</div>
</body>
</html>