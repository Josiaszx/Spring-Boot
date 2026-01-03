package com.implemetacion.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String role;


    // Roles que seran manejados dentro de la app
    @Transient
    final static String USER = "USER";

    @Transient
    final static String ADMIN = "ADMIN";

    @Transient
    final static String GUEST = "GUEST";

    
}
