package com.jinuk.sutdy.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {
    /*
    @WebMvcTest를 할 때 최소 하나의 @Entity클래스를 필요로 하는 @EnableJpaAuditing를 스캔해버리기 때문에 오류가 발생한다.
    그래서 Application.java에서 @SpringBootApplication과 @EnableJpaAuditing을 분리하기 위해 만든 클래스
    */
}
