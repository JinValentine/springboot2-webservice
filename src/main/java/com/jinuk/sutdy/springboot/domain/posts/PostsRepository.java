package com.jinuk.sutdy.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository<Entity클래스, PK타입>을 상속하면 CRUD 메소드가 자동 생성된다.*/
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
