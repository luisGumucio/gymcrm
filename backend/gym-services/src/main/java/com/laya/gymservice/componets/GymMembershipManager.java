package com.laya.gymservice.componets;

import com.laya.gymservice.DTOs.MemberShipResponse;
import com.laya.gymservice.DTOs.MemberSubscriptionDTO;
import com.laya.gymservice.models.Member;
import com.laya.gymservice.models.Period;
import com.laya.gymservice.models.Subscription;
import com.laya.gymservice.services.MemberService;
import com.laya.gymservice.services.PeriodService;
import com.laya.gymservice.services.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class GymMembershipManager {

    private final MemberService memberService;
    private final SubscriptionService subscriptionService;
    private final PeriodService periodService;

    @Transactional
    public MemberShipResponse registerMemberWithSubscription(MemberSubscriptionDTO memberSubscriptionDTO) {
        Member memberRegistered = memberService.registerMember(memberSubscriptionDTO);
        Subscription subscriptionRegistered = subscriptionService.registerSubscriptionByMember(memberRegistered, memberSubscriptionDTO);
        Period period = periodService.registerPeriodBySubscription(subscriptionRegistered, memberSubscriptionDTO);

        MemberShipResponse memberShipResponse = MemberShipResponse.builder()
                .memberId(memberRegistered.getId())
                .name(memberRegistered.getName())
                .finishedDate(period.getEndDate())
                .build();
        log.info("This is an info log message.");
        return memberShipResponse;
    }
}
