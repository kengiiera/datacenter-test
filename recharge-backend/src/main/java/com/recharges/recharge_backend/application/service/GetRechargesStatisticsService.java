package com.recharges.recharge_backend.application.service;

import com.recharges.recharge_backend.application.port.in.GetRechargesStatisticsUseCase;
import com.recharges.recharge_backend.application.port.out.LoadRechargePort;
import com.recharges.recharge_backend.domain.model.Recharge;
import com.recharges.recharge_backend.infrastructure.rest.dto.response.BasicStatsDto;
import com.recharges.recharge_backend.infrastructure.rest.dto.response.RechargeFullStatsResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio que implementa el caso de uso para obtener estadísticas de recargas.
 */
@Service
public class GetRechargesStatisticsService implements GetRechargesStatisticsUseCase {

    private final LoadRechargePort loadRechargePort;

    public GetRechargesStatisticsService(LoadRechargePort loadRechargePort) {
        this.loadRechargePort = loadRechargePort;
    }

    @Override
    public RechargeFullStatsResponse getStats() {
        // Cargar todas las recargas como domain objects
        List<Recharge> all = loadRechargePort.findAllRecharges();

        // Agrupar por operador
        Map<String, List<Recharge>> byOperator = all.stream()
                .collect(Collectors.groupingBy(r -> r.getOperator().name()));

        List<BasicStatsDto> operatorStats = new ArrayList<>();
        byOperator.forEach((op, recharges) -> {
            long count = recharges.size();
            long total = recharges.stream().mapToLong(Recharge::getAmount).sum();
            operatorStats.add(new BasicStatsDto(op, count, total));
        });

        // Agrupar por vendedor
        Map<String, List<Recharge>> bySeller = all.stream()
                .collect(Collectors.groupingBy(Recharge::getSeller));

        List<BasicStatsDto> sellerStats = new ArrayList<>();
        bySeller.forEach((username, recharges) -> {
            long count = recharges.size();
            long total = recharges.stream().mapToLong(Recharge::getAmount).sum();
            sellerStats.add(new BasicStatsDto(username, count, total));
        });

        // Retornar en un objeto de respuesta
        return new RechargeFullStatsResponse(operatorStats, sellerStats);
    }
}
