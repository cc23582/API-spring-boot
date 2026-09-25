package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.validator;

import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public UsuarioValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validar(Usuario usuario) {

        if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
            throw new IllegalArgumentException(
                    "CPF já cadastrado"
            );
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    "E-mail já cadastrado"
            );
        }

        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new IllegalArgumentException(
                    "Login já cadastrado"
            );
        }
    }
}