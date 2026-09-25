package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service;

import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.repository.UsuarioRepository;
import com.example.SisAcademicoAlunos_19.validator.UsuarioValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UsuarioValidator usuarioValidator
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidator = usuarioValidator;
    }

    public Usuario inserirUsuario(Usuario usuario) {

        usuarioValidator.validar(usuario);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {

        Usuario usuarioExistente = usuarioRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setNomeCompleto(
                usuarioAtualizado.getNomeCompleto()
        );
        usuarioExistente.setDataAniversario(
                usuarioAtualizado.getDataAniversario()
        );
        usuarioExistente.setCelular(
                usuarioAtualizado.getCelular()
        );
        usuarioExistente.setEmail(
                usuarioAtualizado.getEmail()
        );
        usuarioExistente.setLogin(
                usuarioAtualizado.getLogin()
        );
        usuarioExistente.setSenha(
                usuarioAtualizado.getSenha()
        );

        return usuarioRepository.save(usuarioExistente);
    }

    public void excluirUsuario(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException(
                    "Usuário não encontrado"
            );
        }

        usuarioRepository.deleteById(id);
    }
}