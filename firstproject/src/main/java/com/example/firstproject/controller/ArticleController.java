package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired  // controller의 필드에 붙이면, springBoot가 만들어놓은 객체를 가져와서 주입시킴 -> 이게 바로 DI
    private ArticleRepository articleRepository;    // DB에서 데이터를 가져오는 주체
    @Autowired
    private CommentService commentService;

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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 1. id로 db 조회 + 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        // 3. 뷰페이지 반환
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. DB에서 모든 Article 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음 모델에 등록
        model.addAttribute("articleList", articleEntityList);
        // 3. 사용자에게 보여줄 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 1. Db에서 Article 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 Article 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 뷰 반환
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        // 1. 폼을 엔티티로 변환
        Article articleEntity = form.toEntity();
        // 2. DB 업데이트
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        // 3. 뷰 반환
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 1. 데이터 가져오기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 삭제
        if (target != null) {
            articleRepository.delete(target);
            // 휘발성 데이터를 넘겨줄 수 있음
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }

        // 3. 리다이렉션
        return "redirect:/articles";
    }
}
