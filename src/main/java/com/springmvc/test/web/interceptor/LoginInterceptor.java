package com.springmvc.test.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.test.web.rest.RestDAOMybatis;
import com.springmvc.test.web.rest.RestDTO;

// HandlerInterceptorAdapter 클래스를 이용한 Interceptor 구현 (로그인 처리)
//컨트롤러를 이용하지않고 로그인처리 가능.
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired 
	RestDAOMybatis dao; // Mybatis로 만든 UserDAO
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(handler.getClass());
		System.out.println("MyLoginInterCeptor - preHandle");
		
		String id = request.getParameter("userId");
		String pwd = request.getParameter("password");
		
		RestDTO vo = new RestDTO();
		vo.setId(id);
		vo.setPassWord(pwd);
		vo = dao.getUser(vo);
		if( vo == null ) {
			System.out.println("로그인 실패");		
			response.sendRedirect(request.getContextPath());
		}
		else {
			request.getSession().setAttribute("authUser", vo);
			response.sendRedirect(request.getContextPath());
		}
		return false;
	}	
}
