package com.springmvc.test.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.springmvc.test.web.rest.RestDTO;


//@LogOut 어노테이션을 처리하는 핸들러.
public class LogOutHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	//바인딩할 객체를 조작할 수 있는 메소드.
	@Override
	public Object resolveArgument(MethodParameter param, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
				// 1. 파라미터에 @AuthUser와 @LogOut가 붙어 있는지 확인 
				if( supportsParameter(param) == false ) {
					// 내가 해석할 수 있는 파라미터가 아니다.
					return WebArgumentResolver.UNRESOLVED;
				}
				// 6. 여기까지 진행이 되었다면, @AuthUser와 @LogOut이 붙어있는 경우이다.
				HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
				HttpSession session = request.getSession();
				if( session == null) {
					return null;
				}		
				System.out.println("@LogOut 어노테이션 동작 완료");
				session.invalidate();
				return new RestDTO();
			}


	//바인딩할 객체의 클래스를 판단하는 메소드
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		// 2. @AuthUser가 붙어 있는지 확인
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		// 3. @AuthUser가 안붙어 있는 경우
		if( authUser == null ) {
			System.out.println("ddddddddddddddddddd nonononononono");
			return false;
		}	
		System.out.println("@AuthUser 어노테이션 붙어있음");
		// 3. 바인딩할 객체에 @LogOut가 붙어 있는지 확인
		LogOut logOut = parameter.getParameterAnnotation(LogOut.class);
		// 4. @LogOut가 안붙어 있는 경우
		if( logOut == null ) {
			System.out.println("@LogOut 안붙어있음");
			return false;
		}
		System.out.println("@LogOut 어노테이션 붙어있음");
		// 5. @LogOut 어노테이션이 붙어있는 경우.
		return true;
	}
}
