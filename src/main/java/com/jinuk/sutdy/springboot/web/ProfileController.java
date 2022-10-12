package com.jinuk.sutdy.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env; // Profiles 설정과 Property 설정에 접근할 때 사용

    @GetMapping("/profile")
    public String profile() {

        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        // 현재 실행중인 ActiveProfiles를 가져온다. real, oauth, real-db 등이 활성화되어 있따면 모두 담겨있다.

        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        // 배포에 사용될 profiles들을 real 기본배포, real1와 real2는 무중단배포 때 사용한다.

        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
