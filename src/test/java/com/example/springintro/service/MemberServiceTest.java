package com.example.springintro.service;

import com.example.springintro.domain.Member;
import com.example.springintro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {

    // 데이터 클리어시 문제 발생
    // MemberService memberRepository = new MemoryMemberRepository();
    // MemoryMemberRepository memberRepository = new MemberService(memberRepository);

    // 해결방안
    // @BeforeEach를 이용하여 테스트가 서로 영향이 없도록 항상 새로운 리파지토리 객체를 생성하도록 한다.
    // >> 서비스 입장에서는 외부에서 리파지토리를 넣어주는 것 (DI)
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    // 예외가 잘 터지는지도 테스트 필요
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
