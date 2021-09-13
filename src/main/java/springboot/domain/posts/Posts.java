package springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter //클래사 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor //기본 생성자
@Entity //테이블과 링크될 클래스임을 나타낸다
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 생성규칙, 이옵션을 추가해야만 auto_increment가 된다
    private Long id;

    @Column(length = 500, nullable = false) //Column 안써도 인식되지만 설정값을 적어주기 위해 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author)
    {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
