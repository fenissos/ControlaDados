package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Pessoa;

public class PessoaOutputDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private GeneroOutputDto genero;
    private CidadeOutputDto cidade;

    public PessoaOutputDto() {
    }

    public PessoaOutputDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.email = pessoa.getEmail();
        if (pessoa.getGenero() != null) {
            this.genero = new GeneroOutputDto(pessoa.getGenero());
        }
        this.cidade = new CidadeOutputDto(pessoa.getCidade());
    }

    public PessoaOutputDto(Pessoa pessoa, CidadeOutputDto cidade) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.email = pessoa.getEmail();
        if (pessoa.getGenero() != null) {
            this.genero = new GeneroOutputDto(pessoa.getGenero());
        }
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public GeneroOutputDto getGenero() {
        return genero;
    }

    public CidadeOutputDto getCidade() {
        return cidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenero(GeneroOutputDto genero) {
        this.genero = genero;
    }

    public void setCidade(CidadeOutputDto cidade) {
        this.cidade = cidade;
    }
}
