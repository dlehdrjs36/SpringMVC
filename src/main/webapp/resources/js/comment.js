/**
 *  댓글 관련 자바스크립트
 */

/*	function CommentList() {
		$.ajax({
			   url:'getCommentsList',
	            success:function(data){
	                $('#time').append(data);
	            }
		});
	}*/
// Context 개념 이해 예제
	const test = {
					//메소드
					a : function() {
						console.log(this+"a메소드"); // 메소드
						
						//scope 관련 부분
						this.b(); // 메소드의 주체인 test의 a()메소드 호출
						b(); // 메소드 내부에 정의되어있는 함수 a()를 찾는다. 없으면 에러
						},
					b : function() {
						console.log(this+"b메소드"); // 메소드
							const a = function(){
								console.log(this+"global A"); // global
							}
							function b(){
								console.log(this+"global B"); // global
							}
								a();
								b();
						}
				}
	test.a();
	test.b();
//
