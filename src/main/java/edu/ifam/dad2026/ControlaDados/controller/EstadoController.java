package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.model.Estado;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private List<Estado> estados = new ArrayList<>();

    private void carregarDados() {

        estados.add(new Estado("Amazonas", "AM", "13"));
        estados.add(new Estado("Para", "PA", "14"));
        estados.add(new Estado("Sao Paulo", "SP", "15"));

    }

    public EstadoController() {
        carregarDados();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> list() {
        return estados;
    }

    @GetMapping(value = "/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado getById(@PathVariable int index) {
        return estados.get(index);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado create(@RequestBody Estado estado) {

        estados.add(estado);

        return estado;
    }

    @DeleteMapping(value = "/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado delete(@PathVariable int index) {

        return estados.remove(index);
    }

    @PutMapping(value = "/{index}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado update(@PathVariable int index, @RequestBody Estado estado) {

        estados.set(index, estado);

        return estado;
    }
}