package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> list() {

        return estadoRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado getById(@PathVariable Long id) {

        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isPresent()) {
            return estado.get();
        }
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado create(@RequestBody Estado estado) {

        return estadoRepository.save(estado);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {

        estadoRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado update(@PathVariable Long id,
                         @RequestBody Estado estado) {

        estado.setId(id);

        return estadoRepository.save(estado);
    }
}