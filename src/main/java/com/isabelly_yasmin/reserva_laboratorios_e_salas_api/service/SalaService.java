package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service;

import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.repository.SalaRepository;
import com.example.SisAcademicoAlunos_19.validator.SalaValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final SalaValidator salaValidator;

    public SalaService(
            SalaRepository salaRepository,
            SalaValidator salaValidator
    ) {
        this.salaRepository = salaRepository;
        this.salaValidator = salaValidator;
    }

    public Sala inserirSala(Sala sala) {

        salaValidator.validar(sala);

        return salaRepository.save(sala);
    }

    public Optional<Sala> pegarDadosSalaPorId(Integer id) {

        return salaRepository.findById(id);
    }

    public Sala atualizarSala(Sala sala) {

        if (sala.getId() == null) {
            throw new IllegalArgumentException(
                    "Não existe a SALA com o ID informado."
            );
        }

        salaValidator.validar(sala);

        return salaRepository.save(sala);
    }

    public void excluirSalaPorId(Integer id) {

        salaRepository.deleteById(id);
    }

    public List<Sala> pesquisar(
            String codigo,
            String nome,
            Integer capacidade,
            String localizacao,
            String status
    ) {

        List<Sala> resultado = salaRepository.findAll();

        return resultado.stream()
                .filter(sala ->
                        codigo == null ||
                                sala.getCodigo()
                                        .toLowerCase()
                                        .contains(codigo.toLowerCase())
                )
                .filter(sala ->
                        nome == null ||
                                sala.getNome()
                                        .toLowerCase()
                                        .contains(nome.toLowerCase())
                )
                .filter(sala ->
                        capacidade == null ||
                                sala.getCapacidade()
                                        .equals(capacidade)
                )
                .filter(sala ->
                        localizacao == null ||
                                sala.getLocalizacao()
                                        .toLowerCase()
                                        .contains(localizacao.toLowerCase())
                )
                .filter(sala ->
                        status == null ||
                                sala.getStatus()
                                        .equalsIgnoreCase(status)
                )
                .toList();
    }
}