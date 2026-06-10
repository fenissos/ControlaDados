package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.dto.CidadeInputDto;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeOutputDto>> list() {

        List<CidadeOutputDto> cidades = cidadeRepository.findAll()
                .stream()
                .map(CidadeOutputDto::new)
                .toList();

        return ResponseEntity.ok(cidades);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> getById(
            @PathVariable Long id) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            return ResponseEntity.ok(
                    new CidadeOutputDto(cidadeOptional.get())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> create(
            @RequestBody CidadeInputDto cidadeInputDto,
            UriComponentsBuilder uriBuilder) {

        cidadeInputDto.setEstadoRepository(estadoRepository);

        Cidade cidade = cidadeInputDto.build();

        Cidade cidadeSalva = cidadeRepository.save(cidade);

        CidadeOutputDto cidadeOutputDto =
                new CidadeOutputDto(cidadeSalva);

        UriComponents uriComponents =
                uriBuilder.path("/api/cidade/{id}")
                        .buildAndExpand(cidadeOutputDto.getId());

        URI uri = uriComponents.toUri();

        return ResponseEntity.created(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cidadeOutputDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus    > delete(@PathVariable Long id) {

        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            cidadeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeOutputDto> update(
            @PathVariable Long id,
            @RequestBody CidadeInputDto cidadeInputDto) {

        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {

            cidadeInputDto.setEstadoRepository(estadoRepository);

            Cidade cidade = cidadeInputDto.build();
            cidade.setId(id);

            Cidade cidadeSalva = cidadeRepository.save(cidade);

            return ResponseEntity.ok(
                    new CidadeOutputDto(cidadeSalva)
            );
        }

        return ResponseEntity.notFound().build();
    }
}