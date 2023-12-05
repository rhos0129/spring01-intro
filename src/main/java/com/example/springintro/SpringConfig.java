package com.example.springintro;

import com.example.springintro.repository.JdbcMemberRepository;
import com.example.springintro.repository.JdbcTemplateMemberRepository;
import com.example.springintro.repository.MemberRepository;
import com.example.springintro.repository.MemoryMemberRepository;
import com.example.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 스프링 빈으로 수동 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링 빈으로 수동 등록
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // 기존 리포지토리 구현체를 Jdbc리포지토리로 변경 (개방-폐쇄 원칙 OCP)
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
