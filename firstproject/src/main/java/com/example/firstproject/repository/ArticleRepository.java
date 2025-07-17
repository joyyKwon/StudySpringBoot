package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // Crud repo 상속으로 추가 구현없이 상속받은 애들 사용하면 됨.
}
