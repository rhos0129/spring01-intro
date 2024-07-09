package com.example.springintro;

import com.example.springintro.repository.*;
import com.example.springintro.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean // 스프링 빈으로 수동 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링 빈으로 수동 등록
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // 기존 리포지토리 구현체를 Jdbc리포지토리로 변경 (개방-폐쇄 원칙 OCP)
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
