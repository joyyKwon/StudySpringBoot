package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    @Autowired  // controller의 필드에 붙이면, springBoot가 만들어놓은 객체를 가져와서 주입시킴 -> 이게 바로 DI
    private ArticleRepository articleRepository;    // DB에서 데이터를 가져오는 주체

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // PostMapping -> mustache 에서 해당 url로 보내는 action이 post일 때.
    // PostMapping -> URL 요청 접수
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());

        // 1. DTO -> entity로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository 사용해서 Entity -> DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 1. id로 db 조회 + 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 3. 뷰페이지 반환

        return "/articles/show";
    }
}
