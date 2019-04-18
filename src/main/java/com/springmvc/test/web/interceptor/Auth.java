package com.springmvc.test.web.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//유저의 권한 검사를 위한 어노테이션.
@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth {
	
	public enum Role {ADMIN, USER}
	// 이와 같이 작성하면 메서드 위에 @Auth(role=Role.ADMIN)과 같이 작성 가능
	public Role role() default Role.USER;
}
