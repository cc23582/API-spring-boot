package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;

    @Column(name = "data_final", nullable = false)
    private LocalDate dataFinal;

    @Column(name = "hora_inicial", nullable = false)
    private LocalTime horaInicial;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Column(name = "status_reserva", nullable = false, length = 20)
    private String statusReserva;
}