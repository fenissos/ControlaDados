package edu.ifam.dad2026.ControlaDados.config;

import edu.ifam.dad2026.ControlaDados.Repository.GeneroRepository;
import edu.ifam.dad2026.ControlaDados.model.Genero;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GeneroDataLoader implements CommandLineRunner {

    private final GeneroRepository generoRepository;

    public GeneroDataLoader(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public void run(String... args) {

        cadastrarSeNaoExistir("Homem Cis");
        cadastrarSeNaoExistir("Mulher Cis");
        cadastrarSeNaoExistir("Trans");
        cadastrarSeNaoExistir("Não Binário");
        cadastrarSeNaoExistir("Outro");
        cadastrarSeNaoExistir("Prefiro não informar");
    }

    private void cadastrarSeNaoExistir(String nome) {

        if (generoRepository.findByNome(nome).isEmpty()) {
            generoRepository.save(new Genero(nome));
        }
    }
}
