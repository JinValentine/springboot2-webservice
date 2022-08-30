package com.jinuk.sutdy.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/* 스프링부트 테스트와 JUnit 사이에 연결자 역할 */
@RunWith(SpringRunner.class)
/* Web에 집중할 수 있는 어노테이션 Controller 위주 테스트할 때 사용*/
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; /* 웹 API를 테스트 할 떄 사용 (GET, POST)등을 테스트*/

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        /* /hello 주소로 get요청 */
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) /* HTTP Header의 Status를 검증 */
                .andExpect((content().string(hello))); /* 응답 본문 내용 검증 */
    }

    @Test
    public void return_helloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                /* API 테스트할 때 사용될 요청 파라미터이다.
                * param의 값은 String만 허용된다.
                * 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야한다. */
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                /* jsonPath는 JSON 응답값을 필드별로 검증할 수 있는 메소드
                * $를 기준으로 필드명을 명시한다.*/
    }
}