package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto;

import com.example.SisAcademicoAlunos_19.validator.CpfValido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioDTO(

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 11, max = 11, message = "CPF inválido")
        @CpfValido
        String cpf,

        @NotBlank(message = "Campo obrigatório")
        @Size(
                min = 10,
                max = 80,
                message = "Quantidade de caracteres incorreta!"
        )
        String nomeCompleto,

        @NotNull(message = "Campo obrigatório")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataAniversario,

        @NotBlank(message = "Campo obrigatório")
        String celular,

        @NotBlank(message = "Campo obrigatório")
        @Size(
                min = 15,
                max = 80,
                message = "Quantidade de caracteres incorreta!"
        )
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Campo obrigatório")
        String login,

        @NotBlank(message = "Campo obrigatório")
        String senha
) {
}