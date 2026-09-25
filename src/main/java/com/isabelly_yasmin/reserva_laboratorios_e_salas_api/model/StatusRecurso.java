package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status_recurso")
@Data
public class StatusRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;
}