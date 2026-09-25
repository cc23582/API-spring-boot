package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.validator;

import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.StatusRecurso;
import com.example.SisAcademicoAlunos_19.repository.StatusRecursoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusRecursoValidator {

    private final StatusRecursoRepository statusRecursoRepository;

    public StatusRecursoValidator(
            StatusRecursoRepository statusRecursoRepository
    ) {
        this.statusRecursoRepository =
                statusRecursoRepository;
    }

    public void validar(StatusRecurso statusRecurso) {

        Optional<StatusRecurso> encontrado =
                statusRecursoRepository.findByCodigo(
                        statusRecurso.getCodigo()
                );

        if (encontrado.isPresent()
                && (statusRecurso.getId() == null
                || !statusRecurso.getId()
                .equals(encontrado.get().getId()))) {

            throw new RegistroDuplicadoException(
                    "Código do status de recurso já cadastrado"
            );
        }

        Optional<StatusRecurso> encontradoNome =
                statusRecursoRepository.findByNome(
                        statusRecurso.getNome()
                );

        if (encontradoNome.isPresent()
                && (statusRecurso.getId() == null
                || !statusRecurso.getId()
                .equals(encontradoNome.get().getId()))) {

            throw new RegistroDuplicadoException(
                    "Status de recurso já cadastrado"
            );
        }
    }
}