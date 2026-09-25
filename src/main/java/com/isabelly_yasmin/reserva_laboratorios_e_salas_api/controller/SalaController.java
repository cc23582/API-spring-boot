package com.isabelly_yasmin.reserva_laboratorios_e_salas_api.controller;

import com.example.SisAcademicoAlunos_19.controller.dto.ErroResposta;
import com.example.SisAcademicoAlunos_19.controller.dto.SalaDTO;
import com.example.SisAcademicoAlunos_19.exceptions.RegistroDuplicadoException;
import com.example.SisAcademicoAlunos_19.model.Sala;
import com.example.SisAcademicoAlunos_19.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<Object> incluirSala(
            @RequestBody @Valid SalaDTO salaDTO
    ) {

        try {

            Sala sala =
                    salaDTO.mapearDadosParaEntidadeSala();

            Sala salaSalva =
                    salaService.inserirSala(sala);

            SalaDTO resposta = new SalaDTO(
                    salaSalva.getId(),
                    salaSalva.getCodigo(),
                    salaSalva.getNome(),
                    salaSalva.getCapacidade(),
                    salaSalva.getLocalizacao(),
                    salaSalva.getStatus()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resposta);

        } catch (RegistroDuplicadoException e) {

            var erroDTO =
                    ErroResposta.conflito(e.getMessage());

            return ResponseEntity
                    .status(erroDTO.status())
                    .body(erroDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> pegarDadosSala(
            @PathVariable("id") Integer id
    ) {

        Optional<Sala> salaOptional =
                salaService.pegarDadosSalaPorId(id);

        if (salaOptional.isPresent()) {

            Sala sala = salaOptional.get();

            SalaDTO salaDTO = new SalaDTO(
                    sala.getId(),
                    sala.getCodigo(),
                    sala.getNome(),
                    sala.getCapacidade(),
                    sala.getLocalizacao(),
                    sala.getStatus()
            );

            return ResponseEntity.ok(salaDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarSala(
            @PathVariable("id") Integer id,
            @RequestBody @Valid SalaDTO salaDTO
    ) {

        try {

            Optional<Sala> salaOptional =
                    salaService.pegarDadosSalaPorId(id);

            if (salaOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Sala sala = salaOptional.get();

            sala.setCodigo(salaDTO.codigo());
            sala.setNome(salaDTO.nome());
            sala.setCapacidade(salaDTO.capacidade());
            sala.setLocalizacao(salaDTO.localizacao());
            sala.setStatus(salaDTO.status());

            salaService.atualizarSala(sala);

            return ResponseEntity.ok().build();

        } catch (RegistroDuplicadoException e) {

            var erroDTO =
                    ErroResposta.conflito(e.getMessage());

            return ResponseEntity
                    .status(erroDTO.status())
                    .body(erroDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirSala(
            @PathVariable("id") Integer id
    ) {

        Optional<Sala> salaOptional =
                salaService.pegarDadosSalaPorId(id);

        if (salaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        salaService.excluirSalaPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> pesquisarSalas(

            @RequestParam(
                    value = "codigo",
                    required = false
            )
            String codigo,

            @RequestParam(
                    value = "nome",
                    required = false
            )
            String nome,

            @RequestParam(
                    value = "capacidade",
                    required = false
            )
            Integer capacidade,

            @RequestParam(
                    value = "localizacao",
                    required = false
            )
            String localizacao,

            @RequestParam(
                    value = "status",
                    required = false
            )
            String status
    ) {

        List<Sala> resultado =
                salaService.pesquisar(
                        codigo,
                        nome,
                        capacidade,
                        localizacao,
                        status
                );

        List<SalaDTO> lista =
                resultado.stream()
                        .map(sala -> new SalaDTO(
                                sala.getId(),
                                sala.getCodigo(),
                                sala.getNome(),
                                sala.getCapacidade(),
                                sala.getLocalizacao(),
                                sala.getStatus()
                        ))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}