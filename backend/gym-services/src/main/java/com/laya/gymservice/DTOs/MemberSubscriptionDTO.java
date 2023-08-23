package com.laya.gymservice.DTOs;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MemberSubscriptionDTO {

    @NotBlank(message = "{NotBlank.memberSubscriptionDTO.name}")
    private String name;

    @NotBlank(message = "{NotBlank.memberSubscriptionDTO.phone}")
    private String phone;

    @NotNull(message = "{NotNull.memberSubscriptionDTO.startDate}")
    private LocalDate startDate;

    @NotNull(message = "{NotNull.memberSubscriptionDTO.endDate}")
    @Future(message = "{Future.memberSubscriptionDTO.endDate}")
    private LocalDate endDate;

//    @NotBlank(message = "{NotBlank.memberSubscriptionDTO.type}")
//    private String type;

    @NotBlank(message = "{NotBlank.memberSubscriptionDTO.plan}")
    private String plan;

    @NotNull(message = "{NotNull.memberSubscriptionDTO.price}")
    @DecimalMin(value = "0.01", message = "{DecimalMin.memberSubscriptionDTO.price}")
    private BigDecimal price;

    @AssertTrue(message = "{AssertTrue.memberSubscriptionDTO.isEndDateValid}")
    public boolean isEndDateValid() {
        return endDate == null || startDate == null || !endDate.isBefore(startDate);
    }
}
