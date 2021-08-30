package springboot.domain.posts;

public interface PostsRepository extends org.springframework.data.jpa.repository.JpaRepository<Posts, Long> {

    //JpaRepository<Posts, Long>를 상속하면 기본적인 Crud 메소드가 자동으로 생성된다.
}
