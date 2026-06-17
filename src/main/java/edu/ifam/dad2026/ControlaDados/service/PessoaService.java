package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.PessoaRepository;
import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import edu.ifam.dad2026.ControlaDados.dto.PessoaInputDto;
import edu.ifam.dad2026.ControlaDados.dto.PessoaOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeClientServe cidadeClientServe;

    public List<PessoaOutputDto> list() {

        return pessoaRepository.findAll()
                .stream()
                .map(pessoa -> {
                    CidadeOutputDto cidade = cidadeClientServe.buscarPorIbge(
                            pessoa.getCidadeIbge()
                    );

                    return new PessoaOutputDto(pessoa, cidade);
                })
                .toList();
    }

    public Optional<PessoaOutputDto> getById(Long id) {

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {

            Pessoa pessoa = pessoaOptional.get();

            CidadeOutputDto cidade = cidadeClientServe.buscarPorIbge(
                    pessoa.getCidadeIbge()
            );

            return Optional.of(new PessoaOutputDto(pessoa, cidade));
        }

        return Optional.empty();
    }

    public PessoaOutputDto create(PessoaInputDto pessoaInputDto) {

        Pessoa pessoa = pessoaInputDto.build();

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        CidadeOutputDto cidade = cidadeClientServe.buscarPorIbge(
                pessoaSalva.getCidadeIbge()
        );

        return new PessoaOutputDto(pessoaSalva, cidade);
    }

    public Optional<PessoaOutputDto> update(Long id, PessoaInputDto pessoaInputDto) {

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {

            Pessoa pessoa = pessoaInputDto.build();

            pessoa.setId(id);

            Pessoa pessoaSalva = pessoaRepository.save(pessoa);

            CidadeOutputDto cidade = cidadeClientServe.buscarPorIbge(
                    pessoaSalva.getCidadeIbge()
            );

            return Optional.of(new PessoaOutputDto(pessoaSalva, cidade));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            pessoaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}