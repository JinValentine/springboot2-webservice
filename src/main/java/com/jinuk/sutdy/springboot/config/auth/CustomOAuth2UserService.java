package com.jinuk.sutdy.springboot.config.auth;

import com.jinuk.sutdy.springboot.config.auth.dto.OAuthAttributes;
import com.jinuk.sutdy.springboot.config.auth.dto.SessionUser;
import com.jinuk.sutdy.springboot.domain.user.User;
import com.jinuk.sutdy.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override // 서비스로부터 받은 userRequest 데이터를 후처리하는 메서드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*
         토큰, 클라이언트 정보가 들어있는 userRequest를 통해 OAuth 서비스에서 가져온 유저 정보를 담고 있는 OAuth2User를 가져온다.
         마지막에 동일한 이메일을 사용하는 사용자가 이미 데이터베이스에 있으면 세부 정보를 업데이트하고, 그렇지 않으면 새 사용자를 등록합니다.
         */

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        /*
        DefaultOAuth2UserServices는 OAuth2UserService의 구현체이다.
        해당 클래스를 이용해서 userRequest에 있는 정보를 빼낼 수 있다..
        */
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 현재 로그인 진행 중인 (구글, 카카오, 네이버)서비스를 구분하는 코드

        String userNameAttributeName = userRequest.
                getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // OAuth2 로그인 진행 시 (Primary Key와 같은)키가 되는 필드값

       OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
       // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담는다.

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        // 객체 하나만 저장
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {

        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())) // 사용자가 존재할 때의 경우
                .orElse(attributes.toEntity()); // 사용자가 처음 가입할 때의 경우

        return userRepository.save(user);
    }



}
