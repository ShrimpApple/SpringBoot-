package com.company.hello_spring.service;

import com.company.hello_spring.domain.Member;
import com.company.hello_spring.repository.MemberRepository;
import com.company.hello_spring.repository.MemoryMemberRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


     //회원 가입
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

     private void validateDuplicateMember(Member member){
        //같은 이름이 있는 중복 회원 안됨
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조 회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}