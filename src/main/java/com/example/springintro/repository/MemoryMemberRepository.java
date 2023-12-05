package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository // 스프링 빈으로 등록
public class MemoryMemberRepository implements MemberRepository{

    // 일단 Map<Long, Member>에 데이터 저장, id는 Long 사용
    // 실무에서는 동시성 문제를 고려하여 ConcurrentHashMap, AtomicLong을 사용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }

}
