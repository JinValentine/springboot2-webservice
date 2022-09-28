package com.jinuk.sutdy.springboot.config.auth.dto;

import com.jinuk.sutdy.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 세션에 인증된 사용자 정보를 저장하기 위해 직렬화 기능을 가진 Dto
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }


}
