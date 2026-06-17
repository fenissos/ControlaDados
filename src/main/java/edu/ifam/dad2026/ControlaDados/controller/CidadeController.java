package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.dto.CidadeInputDto;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.service.CidadeService;
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
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeOutputDto>> list() {

        return ResponseEntity.ok(cidadeService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> getById(@PathVariable String id) {

        Optional<CidadeOutputDto> cidadeOptional = cidadeService.getById(id);

        if (cidadeOptional.isPresent()) {
            return ResponseEntity.ok(cidadeOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> create(
            @RequestBody CidadeInputDto cidadeInputDto,
            UriComponentsBuilder uriBuilder) {

        CidadeOutputDto cidadeOutputDto = cidadeService.create(cidadeInputDto);

        UriComponents uriComponents = uriBuilder
                .path("/api/cidade/{id}")
                .buildAndExpand(cidadeOutputDto.getIbge());

        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cidadeOutputDto);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> update(
            @PathVariable Long id,
            @RequestBody CidadeInputDto cidadeInputDto) {

        Optional<CidadeOutputDto> cidadeOptional = cidadeService.update(id, cidadeInputDto);

        if (cidadeOptional.isPresent()) {
            return ResponseEntity.ok(cidadeOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removido = cidadeService.delete(id);

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}