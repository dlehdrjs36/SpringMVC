package com.springmvc.test.web.interceptor;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//@AuthUser 세션을 관리하기 위한 인터셉터
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface AuthUser {

}
