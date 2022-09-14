package com.jinuk.sutdy.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 단위테스트가 끝날 때마다 수행되는 메소드
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void return_savePost(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // save 메소드로 테이블에 id값이 없으면 insert, id값이 있으면 update 쿼리를 실행
                .title(title)
                .content(content)
                .author("wlsdnr214@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블의 모든 데이터를 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void save_BaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2022,9,13,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifedDate="+posts.getModifedDate());

        assertThat(posts.getCreatedDate()).isAfter(now); // isAfter() 인자보다 미래일 때 True 리턴
        assertThat(posts.getModifedDate()).isAfter(now);
    }
}