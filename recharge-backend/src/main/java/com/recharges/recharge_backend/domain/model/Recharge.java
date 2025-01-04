package com.recharges.recharge_backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;
import com.recharges.recharge_backend.domain.model.enums.Operator;

/**
 * Modelo de dominio que representa una recarga.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {
    private Long id;
    private Operator operator;
    private String phoneNumber;
    private int amount;
    private String seller;
    private String status;
    private LocalDateTime date;
}
