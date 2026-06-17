package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.dto.PessoaInputDto;
import edu.ifam.dad2026.ControlaDados.dto.PessoaOutputDto;
import edu.ifam.dad2026.ControlaDados.service.PessoaService;
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
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaOutputDto>> list() {

        return ResponseEntity.ok(pessoaService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDto> getById(@PathVariable Long id) {

        Optional<PessoaOutputDto> pessoaOptional = pessoaService.getById(id);

        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(pessoaOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDto> create(
            @RequestBody PessoaInputDto pessoaInputDto,
            UriComponentsBuilder uriBuilder) {

        PessoaOutputDto pessoaOutputDto = pessoaService.create(pessoaInputDto);

        UriComponents uriComponents = uriBuilder
                .path("/api/pessoa/{id}")
                .buildAndExpand(pessoaOutputDto.getId());

        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(pessoaOutputDto);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDto> update(
            @PathVariable Long id,
            @RequestBody PessoaInputDto pessoaInputDto) {

        Optional<PessoaOutputDto> pessoaOptional = pessoaService.update(id, pessoaInputDto);

        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(pessoaOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removido = pessoaService.delete(id);

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}