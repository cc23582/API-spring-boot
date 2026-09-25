package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto;

import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatusRecursoDTO(

        Integer id,

        @NotNull(message = "Campo obrigatório")
        Integer codigo,

        @NotBlank(message = "Campo obrigatório")
        @Size(
                min = 5,
                max = 20,
                message = "Quantidade de caracteres incorreta!"
        )
        String nome

) {

    public StatusRecurso mapearDadosParaEntidade() {

        StatusRecurso statusRecurso =
                new StatusRecurso();

        statusRecurso.setCodigo(this.codigo());
        statusRecurso.setNome(this.nome());

        return statusRecurso;
    }
}