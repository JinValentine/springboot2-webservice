package com.jinuk.sutdy.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {
    /*
    @WebMvcTest를 할 때 @SpringBootApplication과 @EnableJpaAuditing가 같이 있어서 오류가 발생하기 때문에
    둘을 분리하기 위해 만든 클래스
    */
}
