package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.StatusRecurso;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository.StatusRecursoRepository;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.validator.StatusRecursoValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusRecursoService {
    private final StatusRecursoRepository statusRecursoRepository;
    private final StatusRecursoValidator statusRecursoValidator;

    public StatusRecursoService(
            StatusRecursoRepository statusRecursoRepository,
            StatusRecursoValidator statusRecursoValidator
    ) {
        this.statusRecursoRepository =
                statusRecursoRepository;

        this.statusRecursoValidator =
                statusRecursoValidator;
    }

    public StatusRecurso inserirStatusRecurso(StatusRecurso statusRecurso) {
        statusRecursoValidator.validar(statusRecurso);

        return statusRecursoRepository.save(
                statusRecurso
        );
    }

    public Optional<StatusRecurso> buscarPorId(Integer id) {
        return statusRecursoRepository.findById(id);
    }

    public List<StatusRecurso> listar() {
        return statusRecursoRepository.findAll();
    }

    public StatusRecurso atualizar(StatusRecurso statusRecurso) {

        if (statusRecurso.getId() == null) {
            throw new IllegalArgumentException(
                    "Não existe o STATUS DE RECURSO com o ID informado."
            );
        }

        statusRecursoValidator.validar(
                statusRecurso
        );

        return statusRecursoRepository.save(
                statusRecurso
        );
    }

    public void excluir(Integer id) {

        if (!statusRecursoRepository.existsById(id)) {

            throw new IllegalArgumentException(
                    "Status de recurso não encontrado."
            );
        }

        statusRecursoRepository.deleteById(id);
    }
}