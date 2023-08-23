package com.laya.gymservice.services;

import com.laya.gymservice.DTOs.MemberSubscriptionDTO;
import com.laya.gymservice.models.Member;
import com.laya.gymservice.models.Subscription;
import com.laya.gymservice.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription registerSubscriptionByMember(Member member, MemberSubscriptionDTO memberSubscriptionDTO) {
        Subscription subscription = Subscription.builder()
                .member(member)
                .joinDate(memberSubscriptionDTO.getStartDate())
                .build();

        subscriptionRepository.save(subscription);
        return subscription;
    }
}
