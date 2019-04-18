package com.springmvc.test.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.springmvc.test.web.rest.RestDTO;
import com.springmvc.test.web.rest.RestService;

//Rest에서 사용하는 서비스 사용하여 구현.
@Controller
public class InterceptorController {
	@Autowired
	RestService userService2;
	
	//회원 정보 수정화면 이동 ( @AuthUser 활용- 세션관리 어노테이션 ) 
	
	@RequestMapping(value="/modify/{id}",  method=RequestMethod.GET)
	@Auth
	public String getUser(@PathVariable String id, @AuthUser RestDTO vo, Model model) {
		System.out.println(id);
		vo.setId(id);
		RestDTO dto = userService2.getUser(vo);
		model.addAttribute("member", dto);
		
		return "member/memberUpdateForm";
	}
	
	//회원 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@LogOut @AuthUser RestDTO vo, Model model) {
		
		System.out.println(vo.getId() + ", " + vo.getName());
		System.out.println(vo);
//		model.addAttribute("authUser", vo);
		
		return "redirect:login.do";
	}
	
}
