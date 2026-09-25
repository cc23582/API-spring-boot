package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository;

import com.example.SisAcademicoAlunos_19.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

    Optional<Sala> findByCodigo(String codigo);

    List<Sala> findByNomeContainingIgnoreCase(String nome);

    List<Sala> findByCapacidade(Integer capacidade);

    List<Sala> findByLocalizacaoContainingIgnoreCase(String localizacao);

    List<Sala> findByStatus(String status);

    Optional<Sala> findByNomeAndCapacidadeAndLocalizacao(
            String nome,
            Integer capacidade,
            String localizacao
    );
}