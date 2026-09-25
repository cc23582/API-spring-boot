package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByLogin(String login);

    List<Usuario> findByCpfContaining(String cpf);

    List<Usuario> findByNomeCompletoContainingIgnoreCase(String nome);

    List<Usuario> findByEmailContainingIgnoreCase(String email);

    List<Usuario> findByDataAniversario(LocalDate dataAniversario);
}