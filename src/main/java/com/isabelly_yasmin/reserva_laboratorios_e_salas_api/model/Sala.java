package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sala")
@Data
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "capacidade", nullable = false)
    private Integer capacidade;

    @Column(name = "localizacao", nullable = false, length = 50)
    private String localizacao;

    @Column(name = "status", nullable = false, length = 20)
    private String status;
}