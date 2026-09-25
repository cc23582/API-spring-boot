package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioDTO;
import com.example.SisAcademicoAlunos_19.controller.dto.UsuarioRespostaDTO;
import com.example.SisAcademicoAlunos_19.model.Usuario;
import com.example.SisAcademicoAlunos_19.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> inserir(
            @Valid @RequestBody UsuarioDTO dto
    ) {

        Usuario usuario = new Usuario();

        usuario.setCpf(dto.cpf());
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setDataAniversario(dto.dataAniversario());
        usuario.setCelular(dto.celular());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());

        Usuario usuarioSalvo =
                usuarioService.inserirUsuario(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        UsuarioRespostaDTO.fromEntity(usuarioSalvo)
                );
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> listar() {

        List<UsuarioRespostaDTO> usuarios =
                usuarioService.listarUsuarios()
                        .stream()
                        .map(UsuarioRespostaDTO::fromEntity)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespostaDTO> buscarPorId(
            @PathVariable Long id
    ) {

        return usuarioService.buscarPorId(id)
                .map(usuario ->
                        ResponseEntity.ok(
                                UsuarioRespostaDTO.fromEntity(usuario)
                        )
                )
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespostaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto
    ) {

        Usuario usuario = new Usuario();

        usuario.setCpf(dto.cpf());
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setDataAniversario(dto.dataAniversario());
        usuario.setCelular(dto.celular());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());

        Usuario usuarioAtualizado =
                usuarioService.atualizarUsuario(
                        id,
                        usuario
                );

        return ResponseEntity.ok(
                UsuarioRespostaDTO.fromEntity(
                        usuarioAtualizado
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {

        usuarioService.excluirUsuario(id);

        return ResponseEntity.noContent().build();
    }
}