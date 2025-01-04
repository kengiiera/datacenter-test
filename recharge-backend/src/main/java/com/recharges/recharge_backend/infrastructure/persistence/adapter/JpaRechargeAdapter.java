package com.recharges.recharge_backend.infrastructure.persistence.adapter;

import com.recharges.recharge_backend.application.port.out.LoadRechargePort;
import com.recharges.recharge_backend.application.port.out.SaveRechargePort;
import com.recharges.recharge_backend.domain.model.enums.Operator;
import com.recharges.recharge_backend.domain.model.Recharge;
import com.recharges.recharge_backend.infrastructure.persistence.entities.RechargeEntity;
import com.recharges.recharge_backend.infrastructure.persistence.entities.UserEntity;
import com.recharges.recharge_backend.infrastructure.persistence.mapper.RechargeMapper;
import com.recharges.recharge_backend.infrastructure.persistence.repository.RechargeRepository;
import com.recharges.recharge_backend.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador JPA que implementa los puertos de salida para recargas.
 */
@Component
public class JpaRechargeAdapter implements SaveRechargePort, LoadRechargePort {

    private final RechargeRepository rechargeRepository;
    private final UserRepository userRepository;
    private final RechargeMapper rechargeMapper;

    public JpaRechargeAdapter(RechargeRepository rechargeRepository, UserRepository userRepository, RechargeMapper rechargeMapper) {
        this.rechargeRepository = rechargeRepository;
        this.userRepository = userRepository;
        this.rechargeMapper = rechargeMapper;
    }

    // ***** Implementación de SaveRechargePort *****
    @Override
    public Recharge save(Recharge recharge) {
        // 1) Buscar userEntity
        UserEntity user = userRepository.findById(recharge.getSeller())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + recharge.getSeller()));

        // 2) Mapear dominio -> entidad
        RechargeEntity entity = rechargeMapper.toEntity(recharge);
        entity.setSeller(user);
        // Asignar fecha si no está seteada
        if (recharge.getDate() == null) {
            entity.setDate(LocalDateTime.now());
        }

        // 3) Guardar
        RechargeEntity saved = rechargeRepository.save(entity);

        // 4) Mapear entidad -> dominio
        return rechargeMapper.toDomain(saved);
    }

    // ***** Implementación de LoadRechargePort *****

    @Override
    public List<Recharge> findAllRecharges() {
        return rechargeRepository.findAll()
                .stream()
                .map(rechargeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recharge> findBySeller(String username) {
        return rechargeRepository.findBySellerUsername(username)
                .stream()
                .map(rechargeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recharge> findByOperator(Operator operator) {
        return rechargeRepository.findByOperator(operator)
                .stream()
                .map(rechargeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
