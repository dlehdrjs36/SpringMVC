package com.springmvc.test.web.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	// 수학 에러 발생
	@RequestMapping(value = "/Arithmetic.do")
	public int ArithmeticError(Model model) {
		int a = 5/0;
		return a;
	}
	// 널 포인터에러 발생
	@RequestMapping(value = "/NullPointer.do")
	public Integer NullPointerError(Model model) {
		Integer error = null;
		error.doubleValue();
		return error;
	}
	// 위의 에러와 다른 에러, 배열길이 에러
	@RequestMapping(value = "/ArrayLangth.do")
	public int[] ArrayError(Model model) {
		int[] a = new int[9];
		a[10] = 1;
		
		return a;
	}
}
