package com.recharges.recharge_backend.infrastructure.rest.dto.response;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO para responder las recargas registradas.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RechargeResponse {
    private Long id;
    private String operator;
    private String phoneNumber;
    private int amount;
    private String seller;
    private String status;
    private LocalDateTime date;
}
