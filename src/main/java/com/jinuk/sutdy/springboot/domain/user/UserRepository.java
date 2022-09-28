package com.jinuk.sutdy.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // email을 통해 사용자의 생성여부를 판단하기 위한 메소드
}
