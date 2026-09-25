package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller;

import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto.ErroResposta;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller.dto.StatusRecursoDTO;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.exceptions.RegistroDuplicadoException;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.model.StatusRecurso;
import com.isabelly_yasmin.reserva_laboratorios_e_salas_api.service.StatusRecursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/status-recursos")
public class StatusRecursoController {
    private final StatusRecursoService statusRecursoService;

    public StatusRecursoController(StatusRecursoService statusRecursoService) {
        this.statusRecursoService = statusRecursoService;
    }

    @PostMapping
    public ResponseEntity<Object> incluirStatusRecurso(
            @RequestBody @Valid StatusRecursoDTO dto
    ) {
        try {
            StatusRecurso statusRecurso =
                    dto.mapearDadosParaEntidade();

            StatusRecurso salvo =
                    statusRecursoService
                            .inserirStatusRecurso(
                                    statusRecurso
                            );

            StatusRecursoDTO resposta =
                    new StatusRecursoDTO(
                            salvo.getId(),
                            salvo.getCodigo(),
                            salvo.getNome()
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resposta);

        } catch (RegistroDuplicadoException e) {
            var erroDTO =
                    ErroResposta.conflito(
                            e.getMessage()
                    );

            return ResponseEntity
                    .status(erroDTO.status())
                    .body(erroDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<StatusRecursoDTO>> listar() {
        List<StatusRecursoDTO> lista =
                statusRecursoService.listar()
                        .stream()
                        .map(status ->
                                new StatusRecursoDTO(
                                        status.getId(),
                                        status.getCodigo(),
                                        status.getNome()
                                )
                        )
                        .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusRecursoDTO> buscarPorId(
            @PathVariable Integer id
    ) {
        Optional<StatusRecurso> optional =
                statusRecursoService.buscarPorId(id);

        if (optional.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        StatusRecurso status =
                optional.get();

        return ResponseEntity.ok(
                new StatusRecursoDTO(
                        status.getId(),
                        status.getCodigo(),
                        status.getNome()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid StatusRecursoDTO dto
    ) {
        try {
            Optional<StatusRecurso> optional =
                    statusRecursoService.buscarPorId(id);

            if (optional.isEmpty()) {
                return ResponseEntity
                        .notFound()
                        .build();
            }

            StatusRecurso status =
                    optional.get();

            status.setCodigo(dto.codigo());
            status.setNome(dto.nome());

            StatusRecurso atualizado =
                    statusRecursoService.atualizar(
                            status
                    );

            return ResponseEntity.ok(
                    new StatusRecursoDTO(
                            atualizado.getId(),
                            atualizado.getCodigo(),
                            atualizado.getNome()
                    )
            );

        } catch (RegistroDuplicadoException e) {
            var erroDTO =
                    ErroResposta.conflito(
                            e.getMessage()
                    );

            return ResponseEntity
                    .status(erroDTO.status())
                    .body(erroDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id
    ) {
        Optional<StatusRecurso> optional =
                statusRecursoService.buscarPorId(id);

        if (optional.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        statusRecursoService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}