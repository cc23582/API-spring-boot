package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto.ReservaConsultaDTO;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Reserva;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service.ReservaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<ReservaConsultaDTO>>
    pesquisarReservas(

            @RequestParam(
                    value = "codigoRecurso",
                    required = false
            )
            String codigoRecurso,

            @RequestParam(
                    value = "nomeRecurso",
                    required = false
            )
            String nomeRecurso,

            @RequestParam(
                    value = "dataInicial",
                    required = false
            )
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataInicial,

            @RequestParam(
                    value = "dataFinal",
                    required = false
            )
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataFinal,

            @RequestParam(
                    value = "horaInicial",
                    required = false
            )
            @DateTimeFormat(pattern = "HH:mm:ss")
            LocalTime horaInicial,

            @RequestParam(
                    value = "horaFinal",
                    required = false
            )
            @DateTimeFormat(pattern = "HH:mm:ss")
            LocalTime horaFinal,

            @RequestParam(
                    value = "cpfUsuario",
                    required = false
            )
            String cpfUsuario,

            @RequestParam(
                    value = "nomeUsuario",
                    required = false
            )
            String nomeUsuario,

            @RequestParam(
                    value = "statusReserva",
                    required = false
            )
            String statusReserva
    ) {

        List<Reserva> resultado =
                reservaService.pesquisarReservas(

                        codigoRecurso,

                        nomeRecurso,

                        dataInicial,

                        dataFinal,

                        horaInicial,

                        horaFinal,

                        cpfUsuario,

                        nomeUsuario,

                        statusReserva
                );

        List<ReservaConsultaDTO> lista =
                resultado.stream()
                        .map(
                                ReservaConsultaDTO::fromEntity
                        )
                        .collect(
                                Collectors.toList()
                        );

        return ResponseEntity.ok(lista);
    }
}