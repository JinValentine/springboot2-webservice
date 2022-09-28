package com.jinuk.sutdy.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"), // 스프링 시큐리티에서는 ROLE_ 이 항상 앞에 붙어있어야 한다.
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
