package com.recharges.recharge_backend.infrastructure.rest.dto.response;

import lombok.*;

/**
 * Representa la información agregada: nombre (operador o vendedor), cantidad de recargas y monto total.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicStatsDto {
    private String name;
    private long totalCount;
    private long totalAmount;
}
