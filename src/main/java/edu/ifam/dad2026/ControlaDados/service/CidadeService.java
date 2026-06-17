package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.dto.CidadeInputDto;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
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
    private EstadoClientServe estadoClientServe;

    public List<CidadeOutputDto> list() {
        return cidadeRepository.findAll()
                .stream()
                .map(cidade -> {
                    EstadoOutputDto estado =
                            estadoClientServe.buscarPorIbge(cidade.getEstadoIbge());

                    return new CidadeOutputDto(cidade, estado);
                })
                .toList();
    }

    public Optional<CidadeOutputDto> getById(String ibge) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findByIbge(ibge);

        if (cidadeOptional.isPresent()) {
            Cidade cidade = cidadeOptional.get();

            EstadoOutputDto estado =
                    estadoClientServe.buscarPorIbge(cidade.getEstadoIbge());

            return Optional.of(
                    new CidadeOutputDto(cidade, estado)
            );
        }

        return Optional.empty();
    }

    public CidadeOutputDto create(CidadeInputDto cidadeInputDto) {

        EstadoOutputDto estado =
                estadoClientServe.buscarPorIbge(cidadeInputDto.getEstadoIbge());

        if (estado == null) {
            throw new RuntimeException("Estado não encontrado para o IBGE: "
                    + cidadeInputDto.getEstadoIbge());
        }

        Cidade cidade = cidadeInputDto.build();

        Cidade cidadeSalva =
                cidadeRepository.save(cidade);

        return new CidadeOutputDto(cidadeSalva, estado);
    }

    public Optional<CidadeOutputDto> update(Long id, CidadeInputDto cidadeInputDto) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {

            EstadoOutputDto estado =
                    estadoClientServe.buscarPorIbge(cidadeInputDto.getEstadoIbge());

            if (estado == null) {
                throw new RuntimeException("Estado não encontrado para o IBGE: "
                        + cidadeInputDto.getEstadoIbge());
            }

            Cidade cidade = cidadeInputDto.build();
            cidade.setId(id);

            Cidade cidadeSalva =
                    cidadeRepository.save(cidade);

            return Optional.of(
                    new CidadeOutputDto(cidadeSalva, estado)
            );
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            cidadeRepository.deleteById(id);
            return true;
        }

        return false;
    }
}