package edu.ifam.dad2026.ControlaDados.controller;

import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> list() {

        return cidadeRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade getById(@PathVariable Long id) {

        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isPresent()) {
            return cidade.get();
        }
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade create(@RequestBody Cidade cidade) {

        return cidadeRepository.save(cidade);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {

        cidadeRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade update(@PathVariable Long id,
                         @RequestBody Cidade cidade) {

        cidade.setId(id);

        return cidadeRepository.save(cidade);
    }
}