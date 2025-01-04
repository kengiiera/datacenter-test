package com.recharges.recharge_backend.application.port.in;

import com.recharges.recharge_backend.domain.model.Recharge;

/**
 * Puerto de entrada que define el caso de uso para registrar una recarga.
 */
public interface RegisterRechargeUseCase {
    /**
     * Registra una nueva recarga.
     *
     * @param recharge El objeto Recharge con los datos de la recarga.
     * @return El objeto Recharge registrado con su ID y otros campos actualizados.
     */
    Recharge registerRecharge(Recharge recharge);
}
