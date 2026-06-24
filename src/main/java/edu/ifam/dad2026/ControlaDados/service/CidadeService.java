package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.dto.CidadeInputDto;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import edu.ifam.dad2026.ControlaDados.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoClientServe estadoClientServe;

    public List<CidadeOutputDto> list() {
        return cidadeRepository.findAll()
                .stream()
                .map(CidadeOutputDto::new)
                .toList();
    }

    public Optional<CidadeOutputDto> getById(String ibge) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findByIbge(ibge);

        if (cidadeOptional.isPresent()) {
            Cidade cidade = cidadeOptional.get();

            return Optional.of(
                    new CidadeOutputDto(cidade)
            );
        }

        return Optional.empty();
    }

    public CidadeOutputDto create(CidadeInputDto cidadeInputDto) {

        Estado estado = buscarEstadoObrigatorio(cidadeInputDto.getEstadoIbge());

        Cidade cidade = cidadeInputDto.build(estado);

        Cidade cidadeSalva =
                cidadeRepository.save(cidade);

        return new CidadeOutputDto(cidadeSalva);
    }

    public Optional<CidadeOutputDto> update(Long id, CidadeInputDto cidadeInputDto) {

        Optional<Cidade> cidadeOptional =
                cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {

            Estado estado = buscarEstadoObrigatorio(cidadeInputDto.getEstadoIbge());

            Cidade cidade = cidadeInputDto.build(estado);
            cidade.setId(id);

            Cidade cidadeSalva =
                    cidadeRepository.save(cidade);

            return Optional.of(
                    new CidadeOutputDto(cidadeSalva)
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

    private Estado buscarEstadoObrigatorio(String estadoIbge) {

        EstadoOutputDto estadoRemoto = estadoClientServe.buscarPorIbge(estadoIbge);

        if (estadoRemoto == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estado não encontrado para o IBGE: " + estadoIbge
            );
        }

        return estadoRepository.findByIbge(estadoRemoto.getIbge())
                .map(estado -> atualizarEstadoLocal(estado, estadoRemoto))
                .orElseGet(() -> salvarEstadoLocal(estadoRemoto));
    }

    private Estado salvarEstadoLocal(EstadoOutputDto estadoRemoto) {

        Estado estado = new Estado(
                estadoRemoto.getNome(),
                estadoRemoto.getSigla(),
                estadoRemoto.getIbge()
        );

        return estadoRepository.save(estado);
    }

    private Estado atualizarEstadoLocal(Estado estado, EstadoOutputDto estadoRemoto) {

        estado.setNome(estadoRemoto.getNome());
        estado.setSigla(estadoRemoto.getSigla());
        estado.setIbge(estadoRemoto.getIbge());

        return estadoRepository.save(estado);
    }
}
