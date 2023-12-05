package com.example.springintro.controller;

import com.example.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 빈으로 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 빈으로 등록되어 있는 객체를 찾아서 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
