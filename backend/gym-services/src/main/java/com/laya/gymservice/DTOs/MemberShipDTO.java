package com.laya.gymservice.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberShipDTO {

    private long memberId;
    private String name;
    private String finishedDate;
}
