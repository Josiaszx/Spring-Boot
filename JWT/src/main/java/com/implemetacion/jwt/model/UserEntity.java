package com.implemetacion.jwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class) // anotacion para ejecutar metodos antes y despues de guardar
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 50, name = "nombre", nullable = false)
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacio")
    private String firstName;

    @Column(length = 50, name = "apellido", nullable = false)
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;

    @Column(unique = true, nullable = false, name = "email")
    @NotNull(message = "El email no puede ser nulo")
    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El email debe ser valido")
    private String email;


    @Column(nullable = false, name = "password")
    @NotNull(message = "la contraseña no puede ser nula")
    @NotBlank(message = "la contraseña no puede ser nula")
    private String password;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "last_updated_at", nullable = false)
    @LastModifiedDate
    private LocalDate lastUpdatedAt;

    @OneToOne
    @JoinColumn(name = "rol_id")
    private Role rolDeUsuario;


}
