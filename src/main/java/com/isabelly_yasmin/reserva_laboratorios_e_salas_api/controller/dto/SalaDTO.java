package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto;

import com.example.SisAcademicoAlunos_19.model.Sala;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaDTO(

        Integer id,

        @NotBlank(message = "Campo obrigatório")
        String codigo,

        @NotBlank(message = "Campo obrigatório")
        @Size(
                min = 11,
                max = 80,
                message = "Quantidade de caracteres incorreta!"
        )
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Min(
                value = 1,
                message = "Valor fora do escopo"
        )
        @Max(
                value = 40,
                message = "Valor fora do escopo"
        )
        Integer capacidade,

        @NotBlank(message = "Campo obrigatório")
        @Size(
                min = 15,
                max = 50,
                message = "Quantidade de caracteres incorreta!"
        )
        String localizacao,

        @NotBlank(message = "Campo obrigatório")
        String status

) {

    public Sala mapearDadosParaEntidadeSala() {

        Sala sala = new Sala();

        sala.setCodigo(this.codigo());
        sala.setNome(this.nome());
        sala.setCapacidade(this.capacidade());
        sala.setLocalizacao(this.localizacao());
        sala.setStatus(this.status());

        return sala;
    }
}