package com.recharges.recharge_backend.infrastructure.persistence.repository;

import com.recharges.recharge_backend.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para gestionar usuarios.
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

}
