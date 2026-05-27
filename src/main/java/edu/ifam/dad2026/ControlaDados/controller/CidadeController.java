package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.model.Cidade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    private List<Cidade> cidades = new ArrayList<>();

    private void carregarDados() {

        cidades.add(new Cidade("Manaus", "1302603","Amazonas"));
        cidades.add(new Cidade("Belém", "1501402","Pará"));
        cidades.add(new Cidade("São Paulo","3550308","São Paulo"));
    }

    public CidadeController() {
        carregarDados();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> list() {

        return cidades;
    }

    @GetMapping(value = "/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade getById(@PathVariable int index) {

        return cidades.get(index);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade create(@RequestBody Cidade cidade) {

        cidades.add(cidade);

        return cidade;
    }

    @PutMapping(value = "/{index}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade update(@PathVariable int index,
                         @RequestBody Cidade cidade) {

        cidades.set(index, cidade);

        return cidade;
    }

    @DeleteMapping(value = "/{index}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade delete(@PathVariable int index) {

        return cidades.remove(index);
    }
}