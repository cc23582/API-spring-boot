package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Usuario;

import java.time.LocalDate;

public record UsuarioRespostaDTO(
        Long id,

        String cpf,

        String nomeCompleto,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataAniversario,

        String celular,

        String email,

        String login
) {

    public static UsuarioRespostaDTO fromEntity(Usuario usuario) {
        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNomeCompleto(),
                usuario.getDataAniversario(),
                usuario.getCelular(),
                usuario.getEmail(),
                usuario.getLogin()
        );
    }
}