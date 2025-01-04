package com.recharges.recharge_backend.application.port.out;

import com.recharges.recharge_backend.domain.model.Recharge;

/**
 * Puerto de salida que define la operación para guardar una recarga.
 */
public interface SaveRechargePort {
    /**
     * Guarda una recarga en la fuente de datos.
     *
     * @param recharge El objeto Recharge a guardar.
     * @return El objeto Recharge guardado con su ID asignado.
     */
    Recharge save(Recharge recharge);
}
