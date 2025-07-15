package org.SenApp.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Usuario_ID")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Correo_electronico", nullable = false, unique = true)
    private String email;

    @Column(name = "Contraseña", nullable = false)
    private String contrasena;

    @Column(name = "Rol", nullable = false)
    private String rol;

    @Column(name = "Nivel_dificultad_actual", nullable = false)
    private String nivel;

    public Usuario(String email, String contrasena, String nombre) {
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.rol = "usuario";
        this.nivel = "básico";
    }
}
