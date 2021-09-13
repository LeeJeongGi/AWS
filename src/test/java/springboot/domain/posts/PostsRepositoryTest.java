package springboot.domain.posts;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
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

    @After //junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        //postsRepository.save tabel posts에 insert/update 쿼리를 싱행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojodu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //findAll은 테이블 posts에 있는 모든데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록()
    {
        //given
        LocalDateTime now = LocalDateTime.of(2021,9,13,9,31,10);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createdDate : " + posts.getCreatedDatd()
        + ", modifiedDate : " + posts.getModifiedDatd());

        assertThat(posts.getCreatedDatd()).isAfter(now);
        assertThat(posts.getModifiedDatd()).isAfter(now);
    }
}
