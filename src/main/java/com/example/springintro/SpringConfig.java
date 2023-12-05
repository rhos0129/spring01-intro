package com.example.springintro;

import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import com.example.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 스프링 빈으로 수동 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링 빈으로 수동 등록
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
