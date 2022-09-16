package com.jinuk.sutdy.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
        // Mustache starter덕분에 컨트롤러에서 문자열을 반환 할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다. (ViewResolver)
    }
}
