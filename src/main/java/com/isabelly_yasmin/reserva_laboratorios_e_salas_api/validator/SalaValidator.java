package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.validator;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.exceptions.RegistroDuplicadoException;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.Sala;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.repository.SalaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SalaValidator {
    private final SalaRepository salaRepository;

    public SalaValidator(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public void validar(Sala sala) {
        Optional<Sala> salaEncontrada =
                salaRepository.findByCodigo(sala.getCodigo());

        if (salaEncontrada.isPresent()
                && (sala.getId() == null
                || !sala.getId().equals(salaEncontrada.get().getId()))) {

            throw new RegistroDuplicadoException(
                    "Código da sala já cadastrado"
            );
        }
    }
}