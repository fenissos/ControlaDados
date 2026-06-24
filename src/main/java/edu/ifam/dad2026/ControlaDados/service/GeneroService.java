package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.GeneroRepository;
import edu.ifam.dad2026.ControlaDados.dto.GeneroInputDto;
import edu.ifam.dad2026.ControlaDados.dto.GeneroOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public List<GeneroOutputDto> list() {

        return generoRepository.findAll()
                .stream()
                .map(GeneroOutputDto::new)
                .toList();
    }

    public Optional<GeneroOutputDto> getById(Long id) {

        return generoRepository.findById(id)
                .map(GeneroOutputDto::new);
    }

    public GeneroOutputDto create(GeneroInputDto generoInputDto) {

        Genero genero = generoInputDto.build();

        Genero generoSalvo = generoRepository.save(genero);

        return new GeneroOutputDto(generoSalvo);
    }

    public Optional<GeneroOutputDto> update(Long id, GeneroInputDto generoInputDto) {

        Optional<Genero> generoOptional = generoRepository.findById(id);

        if (generoOptional.isPresent()) {
            Genero genero = generoInputDto.build();
            genero.setId(id);

            Genero generoSalvo = generoRepository.save(genero);

            return Optional.of(new GeneroOutputDto(generoSalvo));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {

        Optional<Genero> generoOptional = generoRepository.findById(id);

        if (generoOptional.isPresent()) {
            generoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
