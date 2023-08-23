package com.laya.gymservice.DTOs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberShipResponse {

    private long memberId;
    private String name;
    private LocalDate finishedDate;
}
