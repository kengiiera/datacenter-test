package com.recharges.recharge_backend.infrastructure.rest.controller;

import com.recharges.recharge_backend.application.port.in.GetRechargesStatisticsUseCase;
import com.recharges.recharge_backend.infrastructure.rest.dto.response.RechargeFullStatsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar estadísticas de recargas.
 */
@RestController
@RequestMapping("/api/recharges")
public class StatisticsController {

    private final GetRechargesStatisticsUseCase getRechargesStatisticsUseCase;

    public StatisticsController(GetRechargesStatisticsUseCase getRechargesStatisticsUseCase) {
        this.getRechargesStatisticsUseCase = getRechargesStatisticsUseCase;
    }

    /**
     * Endpoint para obtener estadísticas de recargas.
     * GET /api/recharges/stats
     * Requiere token JWT.
     */
    @GetMapping("/stats")
    public ResponseEntity<RechargeFullStatsResponse> getStats() {
        RechargeFullStatsResponse stats = getRechargesStatisticsUseCase.getStats();
        return ResponseEntity.ok(stats);
    }
}
