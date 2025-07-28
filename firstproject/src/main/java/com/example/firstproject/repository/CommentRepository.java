package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // native query method : 직접 작성한 SQL 쿼리. 이 쿼리를 Repository 메소드로 실행할 수 있게 해줌
    // 방법1. @Query 어노테이션 사용
    //      - nativeQuery= false.JPQL 사용(default) | true: 기존 SQL문 그대로 사용 가능
    // 방법2. orm.xml 파일 사용 -> native query XML
    // 특정 게시글의 모든 댓글 조회 (방법1 사용)
    //      - resources/META-INF/orm.xml 파일 생성
    @Query(value = "SELECT * FROM Comment WHERE article_id=:articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회 (방법2 사용)
    List<Comment> findByNickname(String nickname);
}
