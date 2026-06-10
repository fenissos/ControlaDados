package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.dto.CidadeInputDto;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<CidadeOutputDto> list() {
        return cidadeRepository.findAll()
                .stream()
                .map(CidadeOutputDto::new)
                .toList();
    }

    public Optional<CidadeOutputDto> getById(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        if (cidade.isPresent()) {
            return Optional.of(new CidadeOutputDto(cidade.get()));
        }

        return Optional.empty();
    }

    public CidadeOutputDto create(CidadeInputDto cidadeInputDto) {
        cidadeInputDto.setEstadoRepository(estadoRepository);

        Cidade cidade = cidadeInputDto.build();

        Cidade cidadeSalva = cidadeRepository.save(cidade);

        return new CidadeOutputDto(cidadeSalva);
    }

    public Optional<CidadeOutputDto> update(Long id, CidadeInputDto cidadeInputDto) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            cidadeInputDto.setEstadoRepository(estadoRepository);

            Cidade cidade = cidadeInputDto.build();
            cidade.setId(id);

            Cidade cidadeSalva = cidadeRepository.save(cidade);

            return Optional.of(new CidadeOutputDto(cidadeSalva));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        if (cidade.isPresent()) {
            cidadeRepository.deleteById(id);
            return true;
        }

        return false;
    }
}