package com.example.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 화면
    @GetMapping("/") // 컨트롤러가 정적 파일보다 우선순위가 높다.
    public String home() {
        return "home";
    }

}
