package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.StatusRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRecursoRepository
        extends JpaRepository<StatusRecurso, Integer> {

    Optional<StatusRecurso> findByCodigo(Integer codigo);

    List<StatusRecurso> findByNomeContainingIgnoreCase(
            String nome
    );

    Optional<StatusRecurso> findByNome(String nome);
}