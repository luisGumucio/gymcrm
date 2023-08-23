package com.laya.gymservice.controllers;

import com.laya.gymservice.DTOs.MemberShipResponse;
import com.laya.gymservice.DTOs.MemberSubscriptionDTO;
import com.laya.gymservice.componets.GymMembershipManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class GymMembershipController {

    private final GymMembershipManager membershipManager;

    @PostMapping("/register")
    public ResponseEntity<MemberShipResponse> registerMemberWithSubscription(@RequestBody @Valid MemberSubscriptionDTO memberSubscriptionDTO) {
        MemberShipResponse memberShipResponse = membershipManager.registerMemberWithSubscription(memberSubscriptionDTO);
        return new ResponseEntity<>(memberShipResponse, HttpStatus.CREATED);
    }
}
