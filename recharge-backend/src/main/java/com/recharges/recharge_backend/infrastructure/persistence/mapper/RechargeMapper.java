package com.recharges.recharge_backend.infrastructure.persistence.mapper;

import com.recharges.recharge_backend.domain.model.Recharge;
import com.recharges.recharge_backend.infrastructure.persistence.entities.RechargeEntity;
import com.recharges.recharge_backend.infrastructure.rest.dto.request.RechargeRequest;
import com.recharges.recharge_backend.infrastructure.rest.dto.response.RechargeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para convertir entre RechargeRequest, Recharge, RechargeEntity y RechargeResponse.
 */
@Mapper(componentModel = "spring")
public interface RechargeMapper {

    /**
     * Mapea desde RechargeRequest (DTO de solicitud) a Recharge (modelo de dominio).
     *
     * @param request El objeto RechargeRequest.
     * @return El objeto Recharge.
     */
    Recharge toDomain(RechargeRequest request);

    /**
     * Mapea desde Recharge (modelo de dominio) a RechargeEntity (entidad JPA).
     *
     * @param recharge El objeto Recharge.
     * @return El objeto RechargeEntity.
     */
    @Mapping(target = "seller", ignore = true) // El vendedor se asigna manualmente en el adaptador
    RechargeEntity toEntity(Recharge recharge);

    /**
     * Mapea desde RechargeEntity (entidad JPA) a Recharge (modelo de dominio).
     *
     * @param entity El objeto RechargeEntity.
     * @return El objeto Recharge.
     */
    @Mapping(target = "seller", source = "seller.username")
    Recharge toDomain(RechargeEntity entity);

    /**
     * Mapea desde Recharge (modelo de dominio) a RechargeResponse (DTO de respuesta).
     *
     * @param recharge El objeto Recharge.
     * @return El objeto RechargeResponse.
     */
    RechargeResponse toResponse(Recharge recharge);
}
