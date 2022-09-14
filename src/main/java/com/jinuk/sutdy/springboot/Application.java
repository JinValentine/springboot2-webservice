package com.jinuk.sutdy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing // JPA Auditing 어노테이션들을 모두 활성화할 수 있도록 해준다.
/* 스프링 부트의 자동설정, 스프링 Bean 일기와 생성을 모두 자동으로 설정
*  해당 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 위치해야 한다.*/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /* run으로 내장 WAS를 실행한다. */
        SpringApplication.run(Application.class, args);
    }
}
