package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaConsultaDTO(
        Integer id,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataInicial,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFinal,

        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horaInicial,

        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horaFinal,

        String codigoSala,

        String nomeSala,

        Integer capacidadeSala,

        String localizacaoSala,

        String cpfUsuario,

        String nomeUsuario,

        String emailUsuario,

        String statusReserva
) {

    public static ReservaConsultaDTO fromEntity(Reserva reserva) {

        return new ReservaConsultaDTO(

                reserva.getId(),

                reserva.getDataInicial(),

                reserva.getDataFinal(),

                reserva.getHoraInicial(),

                reserva.getHoraFinal(),

                reserva.getSala().getCodigo(),

                reserva.getSala().getNome(),

                reserva.getSala().getCapacidade(),

                reserva.getSala().getLocalizacao(),

                reserva.getUsuario().getCpf(),

                reserva.getUsuario().getNomeCompleto(),

                reserva.getUsuario().getEmail(),

                reserva.getStatusReserva()
        );
    }
}