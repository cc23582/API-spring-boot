package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository
        extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByDataInicial(
            LocalDate dataInicial
    );

    List<Reserva> findByDataInicialAndHoraInicial(
            LocalDate dataInicial,
            LocalTime horaInicial
    );

    List<Reserva> findBySalaCodigo(
            String codigo
    );

    List<Reserva> findBySalaNomeContainingIgnoreCase(
            String nome
    );

    List<Reserva> findByUsuarioCpf(
            String cpf
    );

    List<Reserva> findByUsuarioNomeCompletoContainingIgnoreCase(
            String nome
    );

    List<Reserva> findByStatusReserva(
            String statusReserva
    );
}