package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "kb");
        model.addAttribute("sentence", "hi!");
        return "grettings";     // 만든 mustache 파일 이름 반환 -> templates 디렉토리에서 해당 파일을 찾아 웹 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("username", "joy");
        model.addAttribute("sentence", "bye~~");
        return "goodbye";
    }
}
