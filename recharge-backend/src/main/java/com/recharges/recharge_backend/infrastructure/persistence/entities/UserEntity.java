package com.recharges.recharge_backend.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad JPA que representa a un usuario.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
