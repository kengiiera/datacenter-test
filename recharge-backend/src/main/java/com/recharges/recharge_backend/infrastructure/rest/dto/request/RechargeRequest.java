package com.recharges.recharge_backend.infrastructure.rest.dto.request;

import com.recharges.recharge_backend.domain.model.enums.Operator;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para recibir las solicitudes de recarga.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RechargeRequest {

    @NotNull(message = "El operador es obligatorio.")
    private Operator operator;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    private String phoneNumber;

    @Min(value = 1, message = "El monto debe ser al menos 1.")
    private int amount;
}
