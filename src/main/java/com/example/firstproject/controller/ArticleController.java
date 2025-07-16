package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // PostMapping -> mustache 에서 해당 url로 보내는 action이 post일 때.
    // PostMapping -> URL 요청 접수
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        // todo 1. DTO -> entity로 변환
        Article article = form.toEntity();
        // todo 2. Repository 사용해서 Entity -> DB에 저장
        return "";
    }
}
