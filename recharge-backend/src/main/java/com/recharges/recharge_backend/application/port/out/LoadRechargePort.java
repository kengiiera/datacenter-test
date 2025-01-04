package com.recharges.recharge_backend.application.port.out;

import com.recharges.recharge_backend.domain.model.enums.Operator;
import com.recharges.recharge_backend.domain.model.Recharge;

import java.util.List;

/**
 * Puerto de salida que define las operaciones para cargar recargas.
 */
public interface LoadRechargePort {
    /**
     * Obtiene todas las recargas registradas.
     *
     * @return Lista de recargas.
     */
    List<Recharge> findAllRecharges();

    /**
     * Obtiene las recargas realizadas por un vendedor específico.
     *
     * @param username Username del vendedor.
     * @return Lista de recargas realizadas por el vendedor.
     */
    List<Recharge> findBySeller(String username);

    /**
     * Obtiene las recargas realizadas a través de un operador específico.
     *
     * @param operator Operador.
     * @return Lista de recargas realizadas a través del operador.
     */
    List<Recharge> findByOperator(Operator operator);
}
