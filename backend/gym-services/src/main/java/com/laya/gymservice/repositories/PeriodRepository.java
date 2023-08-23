package com.laya.gymservice.repositories;

import com.laya.gymservice.models.Period;
import com.laya.gymservice.models.PeriodState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
    Optional<Period> findBySubscriptionId(Long subscriptionId);

    Optional<Period> findByPeriodStateAndSubscriptionId(PeriodState periodState, Long subscriptionId);

    List<Period> findAllByPeriodState(PeriodState periodState);
}
