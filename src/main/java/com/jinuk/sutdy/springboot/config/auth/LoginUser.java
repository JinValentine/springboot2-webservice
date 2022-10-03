package com.jinuk.sutdy.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치를 지정한다. (메소드의 파라미터로 선언된 객체에서만 사용 가능)
@Retention(RetentionPolicy.RUNTIME) // 어느 시점까지 메모리를 가져갈지 정한다. (런타임까지)
public @interface LoginUser { // @interface로 어노테이션 클래스로 지정(생성)한다.
    // SessionUser user = (SessionUser) httpSession.getAttribute("user"); <- 반복을 어노테이션으로 해결한다.
}
