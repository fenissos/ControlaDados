package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.PessoaRepository;
import edu.ifam.dad2026.ControlaDados.Repository.CidadeRepository;
import edu.ifam.dad2026.ControlaDados.dto.PessoaInputDto;
import edu.ifam.dad2026.ControlaDados.dto.PessoaOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import edu.ifam.dad2026.ControlaDados.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<PessoaOutputDto> list() {

        return pessoaRepository.findAll()
                .stream()
                .map(PessoaOutputDto::new)
                .toList();
    }

    public Optional<PessoaOutputDto> getById(Long id) {

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {

            Pessoa pessoa = pessoaOptional.get();

            return Optional.of(new PessoaOutputDto(pessoa));
        }

        return Optional.empty();
    }

    public PessoaOutputDto create(PessoaInputDto pessoaInputDto) {

        Cidade cidade = buscarCidadeObrigatoria(pessoaInputDto.getCidadeIbge());

        Pessoa pessoa = pessoaInputDto.build(cidade);

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        return new PessoaOutputDto(pessoaSalva);
    }

    public Optional<PessoaOutputDto> update(Long id, PessoaInputDto pessoaInputDto) {

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {

            Cidade cidade = buscarCidadeObrigatoria(pessoaInputDto.getCidadeIbge());

            Pessoa pessoa = pessoaInputDto.build(cidade);

            pessoa.setId(id);

            Pessoa pessoaSalva = pessoaRepository.save(pessoa);

            return Optional.of(new PessoaOutputDto(pessoaSalva));
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

    private Cidade buscarCidadeObrigatoria(String cidadeIbge) {

        return cidadeRepository.findByIbge(cidadeIbge)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Cidade não encontrada para o IBGE: " + cidadeIbge
                ));
    }
}
