package com.springmvc.test.web.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmaillController {

	// common email을 이용하여 메일 보내기
	@RequestMapping("/commonemail.do")
	public ModelAndView sendEmail(HttpServletResponse response) throws IOException {
		Map<String, Object> a = new HashMap<String, Object>();
		a.put("a", 1);
		return new ModelAndView("commonEmail", a); //View 정보와 데이터(model) 정보를 같이 가지고 있는 ModelAndView 객체
	}

	// javax email을 이용하여 메일 보내기
	@RequestMapping("/javaxemail.do")
	public ModelAndView javaxEmail() throws IOException {
		return new ModelAndView("javaxEmail"); //View 정보만 담고있는 ModelAndView 객체
	}
}
