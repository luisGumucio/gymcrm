package com.laya.gymservice.controllers;

import com.laya.gymservice.DTOs.MemberShipDTO;
import com.laya.gymservice.models.Attendance;
import com.laya.gymservice.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<?> registerAttendance(@RequestBody MemberShipDTO memberShipDTO) {
        Attendance attendance = attendanceService.registerAttendanceByMember(memberShipDTO);
        if (attendance == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No access");
        }
        attendanceService.evaluateAttendance(attendance);
        return ResponseEntity.ok(attendance);
    }

}
