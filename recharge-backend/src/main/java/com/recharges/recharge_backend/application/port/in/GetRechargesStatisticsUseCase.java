package com.recharges.recharge_backend.application.port.in;

import com.recharges.recharge_backend.infrastructure.rest.dto.response.RechargeFullStatsResponse;

/**
 * Puerto de entrada que define el caso de uso para obtener estadísticas de recargas.
 */
public interface GetRechargesStatisticsUseCase {
    /**
     * Obtiene las estadísticas de recargas agrupadas por operador y vendedor.
     *
     * @return Un objeto RechargeFullStatsResponse con las estadísticas.
     */
    RechargeFullStatsResponse getStats();
}
