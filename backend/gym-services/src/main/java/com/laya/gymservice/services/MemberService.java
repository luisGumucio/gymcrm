package com.laya.gymservice.services;

import com.laya.gymservice.DTOs.MemberSubscriptionDTO;
import com.laya.gymservice.exceptions.MemberNotFoundException;
import com.laya.gymservice.models.Member;
import com.laya.gymservice.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Member registerMember(MemberSubscriptionDTO memberSubscriptionDTO) {
        Member member = Member.builder()
                .name(memberSubscriptionDTO.getName())
                .phone(memberSubscriptionDTO.getPhone())
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member getMemberById(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.orElseThrow(() -> new MemberNotFoundException("Miembro no encontrado"));
    }
}
