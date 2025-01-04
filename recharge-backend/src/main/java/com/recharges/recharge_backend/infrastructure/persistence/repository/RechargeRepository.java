package com.recharges.recharge_backend.infrastructure.persistence.repository;

import com.recharges.recharge_backend.domain.model.enums.Operator;
import com.recharges.recharge_backend.infrastructure.persistence.entities.RechargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio JPA para gestionar recargas.
 */
public interface RechargeRepository extends JpaRepository<RechargeEntity, Long> {

    /**
     * Obtiene todas las recargas realizadas por un vendedor específico.
     *
     * @param username Username del vendedor.
     * @return Lista de recargas.
     */
    @Query("SELECT r FROM RechargeEntity r WHERE r.seller.username = :username")
    List<RechargeEntity> findBySellerUsername(@Param("username") String username);

    /**
     * Obtiene todas las recargas realizadas a través de un operador específico.
     *
     * @param operator Operador.
     * @return Lista de recargas.
     */
    @Query("SELECT r FROM RechargeEntity r WHERE r.operator = :operator")
    List<RechargeEntity> findByOperator(@Param("operator") Operator operator);
}
