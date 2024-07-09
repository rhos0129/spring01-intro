package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository : 스프링 데이터 JPA가 스프링 빈으로 자동 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    Optional<Member> findByName(String name);

}