package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id")  // 외래키 생성. Article 엔티티의 기본키(id)와 매핑
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;
}
