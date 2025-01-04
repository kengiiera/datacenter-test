package com.recharges.recharge_backend.infrastructure.rest.controller;

import com.recharges.recharge_backend.application.port.in.RegisterRechargeUseCase;
import com.recharges.recharge_backend.domain.model.Recharge;
import com.recharges.recharge_backend.infrastructure.persistence.mapper.RechargeMapper;
import com.recharges.recharge_backend.infrastructure.rest.dto.request.RechargeRequest;
import com.recharges.recharge_backend.infrastructure.rest.dto.response.RechargeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar recargas.
 */
@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final RegisterRechargeUseCase registerRechargeUseCase;
    private final RechargeMapper rechargeMapper;

    public RechargeController(RegisterRechargeUseCase registerRechargeUseCase, RechargeMapper rechargeMapper) {
        this.registerRechargeUseCase = registerRechargeUseCase;
        this.rechargeMapper = rechargeMapper;
    }

    /**
     * Endpoint para registrar una recarga.
     * POST /api/recharges
     * Requiere token JWT.
     */
    @PostMapping
    public ResponseEntity<RechargeResponse> registerRecharge(@RequestBody RechargeRequest request) {
        // Mapear RechargeRequest -> Recharge (modelo de dominio)
        Recharge domain = rechargeMapper.toDomain(request);

        // Registrar la recarga
        Recharge saved = registerRechargeUseCase.registerRecharge(domain);

        // Mapear Recharge -> RechargeResponse (DTO de respuesta)
        RechargeResponse response = rechargeMapper.toResponse(saved);

        return ResponseEntity.ok(response);
    }
}
