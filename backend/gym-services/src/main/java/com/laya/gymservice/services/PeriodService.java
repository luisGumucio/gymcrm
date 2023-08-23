package com.laya.gymservice.services;

import com.laya.gymservice.DTOs.MemberSubscriptionDTO;
import com.laya.gymservice.models.Period;
import com.laya.gymservice.models.PeriodState;
import com.laya.gymservice.models.Subscription;
import com.laya.gymservice.repositories.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodService {

    private final PeriodRepository periodRepository;

    public Period registerPeriodBySubscription(Subscription subscription, MemberSubscriptionDTO memberSubscriptionDTO) {
        Period period = Period.builder()
                .startDate(memberSubscriptionDTO.getStartDate())
                .endDate(memberSubscriptionDTO.getEndDate())
                .planName(memberSubscriptionDTO.getPlan())
                .price(memberSubscriptionDTO.getPrice())
                .subscription(subscription)
                .periodState(PeriodState.ACTIVE)
                .type("Simple")
                .build();

        periodRepository.save(period);
        return period;
    }

    public Optional<Period> getPeriodByPeriodStateSubscriptionId(PeriodState periodState, Long subscriptionId) {
        return periodRepository.findByPeriodStateAndSubscriptionId(periodState, subscriptionId);
    }

    public void updatePeriod(Period period) {
        periodRepository.save(period);
    }

//    @Scheduled(cron = "*/30 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    private void evaluatePeriodsActive() {
        LocalDate beforeDate = LocalDate.now().minusDays(1);
        List<Period> periods = periodRepository.findAllByPeriodState(PeriodState.ACTIVE);
        periods.forEach(period -> {
            LocalDate periodDate = period.getEndDate();
            if (beforeDate.isAfter(periodDate) || beforeDate.isEqual(periodDate)) {
                period.setPeriodState(PeriodState.FINISHED);
                updatePeriod(period);
            }
        });
    }
}
