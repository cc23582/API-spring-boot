package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 80)
    private String nomeCompleto;

    @Column(nullable = false)
    private LocalDate dataAniversario;

    @Column(nullable = false, length = 20)
    private String celular;

    @Column(nullable = false, unique = true, length = 80)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String senha;
}