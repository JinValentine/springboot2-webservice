package com.jinuk.sutdy.springboot.web;

import com.jinuk.sutdy.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // model: 객체를 저장할 수 있는 곳
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
        // Mustache starter덕분에 컨트롤러에서 문자열을 반환 할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다. (ViewResolver)
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
