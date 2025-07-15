package org.SenApp.model;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "progreso_usuarios")
public class ProgresoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Progreso_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "Estado", nullable = false)
    private String estado;

    @Column(name = "Fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "Fecha_finalización", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "Puntuación", nullable = false)
    private Integer puntuacion;

    // Constructor por defecto (requerido por JPA)
    public ProgresoUsuario() {
    }

    // Constructor personalizado
    public ProgresoUsuario(Usuario usuario, int puntuacion) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.estado = "completado";
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}
