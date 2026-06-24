package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.dto.GeneroInputDto;
import edu.ifam.dad2026.ControlaDados.dto.GeneroOutputDto;
import edu.ifam.dad2026.ControlaDados.service.GeneroService;
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
@RequestMapping("/api/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GeneroOutputDto>> list() {

        return ResponseEntity.ok(generoService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneroOutputDto> getById(@PathVariable Long id) {

        Optional<GeneroOutputDto> generoOptional = generoService.getById(id);

        if (generoOptional.isPresent()) {
            return ResponseEntity.ok(generoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneroOutputDto> create(
            @RequestBody GeneroInputDto generoInputDto,
            UriComponentsBuilder uriBuilder) {

        GeneroOutputDto generoOutputDto = generoService.create(generoInputDto);

        UriComponents uriComponents = uriBuilder
                .path("/api/genero/{id}")
                .buildAndExpand(generoOutputDto.getId());

        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generoOutputDto);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneroOutputDto> update(
            @PathVariable Long id,
            @RequestBody GeneroInputDto generoInputDto) {

        Optional<GeneroOutputDto> generoOptional = generoService.update(id, generoInputDto);

        if (generoOptional.isPresent()) {
            return ResponseEntity.ok(generoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removido = generoService.delete(id);

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
