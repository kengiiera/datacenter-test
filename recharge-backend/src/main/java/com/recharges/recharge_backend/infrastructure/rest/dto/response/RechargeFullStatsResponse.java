package com.recharges.recharge_backend.infrastructure.rest.dto.response;

import lombok.*;
import java.util.List;

/**
 * Agrega en una sola respuesta la información por operador y por vendedor.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RechargeFullStatsResponse {
    private List<BasicStatsDto> byOperator;
    private List<BasicStatsDto> bySeller;
}
