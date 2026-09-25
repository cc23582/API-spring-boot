package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Reserva;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository =
                reservaRepository;
    }

    public List<Reserva> pesquisarReservas(

            String codigoRecurso,

            String nomeRecurso,

            LocalDate dataInicial,

            LocalDate dataFinal,

            LocalTime horaInicial,

            LocalTime horaFinal,

            String cpfUsuario,

            String nomeUsuario,

            String statusReserva

    ) {
        return reservaRepository.findAll()
                .stream()

                // Filtro por código do recurso
                .filter(reserva ->
                        codigoRecurso == null
                                || reserva.getSala()
                                .getCodigo()
                                .equalsIgnoreCase(
                                        codigoRecurso
                                )
                )

                // Filtro por nome do recurso
                .filter(reserva ->
                        nomeRecurso == null
                                || reserva.getSala()
                                .getNome()
                                .toLowerCase()
                                .contains(
                                        nomeRecurso.toLowerCase()
                                )
                )

                // Filtro por data inicial
                .filter(reserva ->
                        dataInicial == null
                                || !reserva
                                .getDataInicial()
                                .isBefore(dataInicial)
                )

                // Filtro por data final
                .filter(reserva ->
                        dataFinal == null
                                || !reserva
                                .getDataFinal()
                                .isAfter(dataFinal)
                )

                // Filtro por hora inicial
                .filter(reserva ->
                        horaInicial == null
                                || !reserva
                                .getHoraInicial()
                                .isBefore(horaInicial)
                )

                // Filtro por hora final
                .filter(reserva ->
                        horaFinal == null
                                || !reserva
                                .getHoraFinal()
                                .isAfter(horaFinal)
                )

                // Filtro por cpf do usuário
                .filter(reserva ->
                        cpfUsuario == null
                                || reserva.getUsuario()
                                .getCpf()
                                .equals(cpfUsuario)
                )

                // Filtro por nome do usuário
                .filter(reserva ->
                        nomeUsuario == null
                                || reserva.getUsuario()
                                .getNomeCompleto()
                                .toLowerCase()
                                .contains(
                                        nomeUsuario.toLowerCase()
                                )
                )

                // Filtro por status da reserva
                .filter(reserva ->
                        statusReserva == null
                                || reserva
                                .getStatusReserva()
                                .equalsIgnoreCase(
                                        statusReserva
                                )
                )
                .toList();
    }
}