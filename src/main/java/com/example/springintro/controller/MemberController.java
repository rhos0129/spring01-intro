package com.example.springintro.controller;

import com.example.springintro.domain.Member;
import com.example.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 빈으로 등록
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 빈으로 등록되어 있는 객체를 찾아서 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 등록 화면
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 등록 기능
    @PostMapping(value = "/members/new")
    public String create(MemebrForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/"; // 홈 화면으로 리다이렉트
    }

    // 조회 화면
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 데이터 조회 후
        model.addAttribute("members", members); // 화면으로 전달
        return "members/memberList";
    }

}
