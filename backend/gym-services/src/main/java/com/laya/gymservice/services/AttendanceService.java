package com.laya.gymservice.services;

import com.laya.gymservice.DTOs.MemberShipDTO;
import com.laya.gymservice.models.Attendance;
import com.laya.gymservice.models.Member;
import com.laya.gymservice.models.Period;
import com.laya.gymservice.models.PeriodState;
import com.laya.gymservice.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberService memberService;
    private final PeriodService periodService;

    public Attendance registerAttendanceByMember(MemberShipDTO memberShipDTO) {
        Member member = memberService.getMemberById(memberShipDTO.getMemberId());
        Optional<Period> periodByMember = periodService.getPeriodByPeriodStateSubscriptionId(PeriodState.ACTIVE, member.getSubscription().getId());

        if (periodByMember.isEmpty()) {
            log.info("NO access");
            return null;
        }

        Attendance attendance = Attendance.builder()
                .attendanceTime(LocalDateTime.now())
                .member(member)
                .period(periodByMember.get())
                .build();

        attendanceRepository.save(attendance);
        return attendance;
    }

    @Async
    public void evaluateAttendance(Attendance attendance) {
        LocalDate currentDate = LocalDate.now();
        LocalDate periodDate = attendance.getPeriod().getEndDate();
        if (currentDate.isEqual(periodDate)) {
            Period period = attendance.getPeriod();
            period.setPeriodState(PeriodState.FINISHED);
            periodService.updatePeriod(period);
            //TODO add notifications implementations
        }
    }
}
