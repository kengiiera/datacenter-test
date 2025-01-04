package com.recharges.recharge_backend.application.service;

import com.recharges.recharge_backend.application.port.in.RegisterRechargeUseCase;
import com.recharges.recharge_backend.application.port.out.SaveRechargePort;
import com.recharges.recharge_backend.domain.model.Recharge;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa el caso de uso para registrar una recarga.
 */
@Service
public class RegisterRechargeService implements RegisterRechargeUseCase {

    private final SaveRechargePort saveRechargePort;

    public RegisterRechargeService(SaveRechargePort saveRechargePort) {
        this.saveRechargePort = saveRechargePort;
    }

    @Override
    public Recharge registerRecharge(Recharge recharge) {
        // Obtener username autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        recharge.setSeller(username);

        // Asignar status y date
        recharge.setStatus("SUCCESS");
        // La fecha se asigna en el adaptador

        // Guardar usando el puerto
        return saveRechargePort.save(recharge);
    }
}
