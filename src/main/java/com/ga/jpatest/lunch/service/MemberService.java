package com.ga.jpatest.lunch.service;

import com.ga.jpatest.lunch.domain.Member;
import com.ga.jpatest.lunch.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void save(String name) {
        Member member = new Member(name);
        memberRepository.save(member);
    }
}
