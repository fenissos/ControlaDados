package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Pessoa;

public class PessoaOutputDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Long cidadeId;
    private String cidadeNome;
    private Long estadoId;
    private String estadoNome;

    public PessoaOutputDto() {
    }

    public PessoaOutputDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.email = pessoa.getEmail();

        if (pessoa.getCidade() != null) {
            this.cidadeId = pessoa.getCidade().getId();
            this.cidadeNome = pessoa.getCidade().getNome();

            if (pessoa.getCidade().getEstado() != null) {
                this.estadoId = pessoa.getCidade().getEstado().getId();
                this.estadoNome = pessoa.getCidade().getEstado().getNome();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoNome() {
        return estadoNome;
    }

    public void setEstadoNome(String estadoNome) {
        this.estadoNome = estadoNome;
    }
}
