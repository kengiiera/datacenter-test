package com.recharges.recharge_backend.infrastructure.persistence.entities;

import com.recharges.recharge_backend.domain.model.enums.Operator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad JPA que representa una recarga.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recharges")
public class RechargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operator operator;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username_seller", nullable = false)
    private UserEntity seller;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime date;
}
