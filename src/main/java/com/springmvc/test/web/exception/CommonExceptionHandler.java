package com.springmvc.test.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/***
 * <b>어노테이션 기반 예외처리</b>
 * @ControllerAdvice 어노테이션에 의해 CommonExceptionHandler는 자동으로 생성된다.
 * ("com.springmvc.test")의 의미는 com.springmvc.test으로 시작하는 컨트롤러에서 예외가 발생하는 순간,
 * 해당 예외에 대해서 @ExceptionHandler가 지정된 메소드가 실행된다.
 * 
 * <b>XML 기반의 예외처리</b>
 * 	servlet-context.xml 참고 
 */
/*@ControllerAdvice("com.springmvc.test") 
public class CommonExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/arithmeticError");
		return mav;
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/nullPointerError");
		return mav;
	}
	
	// Exception 클래스는 Object클래스를 제외한 예외클래스의 최상위 클래스이다. 모든 예외를 처리할 수 있다.
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/error");
		return mav;
	}
}*/