package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.dto.EstadoInputDto;
import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
import edu.ifam.dad2026.ControlaDados.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoOutputDto>> list() {

        return ResponseEntity.ok(
                estadoService.list()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDto> getById(@PathVariable String id) {

        Optional<EstadoOutputDto> estadoOptional =
                estadoService.getById(id);

        if (estadoOptional.isPresent()) {
            return ResponseEntity.ok(
                    estadoOptional.get()
            );
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDto> create(
            @RequestBody EstadoInputDto estadoInputDto,
            UriComponentsBuilder uriBuilder) {

        EstadoOutputDto estadoOutputDto =
                estadoService.create(estadoInputDto);

        UriComponents uriComponents =
                uriBuilder.path("/api/estado/{id}")
                        .buildAndExpand(estadoOutputDto.getIbge());

        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(estadoOutputDto);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDto> update(
            @PathVariable Long id,
            @RequestBody EstadoInputDto estadoInputDto) {

        Optional<EstadoOutputDto> estadoOptional =
                estadoService.update(id, estadoInputDto);

        if (estadoOptional.isPresent()) {
            return ResponseEntity.ok(
                    estadoOptional.get()
            );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removido =
                estadoService.delete(id);

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}