package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Pessoa;
import edu.ifam.dad2026.ControlaDados.model.Cidade;

public class PessoaInputDto {

    private String nome;
    private String cpf;
    private String email;
    private Integer sexo;
    private String cidadeIbge;

    public PessoaInputDto() {
    }

    public Pessoa build(Cidade cidade) {
        return new Pessoa(nome, cpf, email, sexo, cidade);
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

    public Integer getSexo() {
        return sexo;
    }

    public String getCidadeIbge() {
        return cidadeIbge;
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

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public void setCidadeIbge(String cidadeIbge) {
        this.cidadeIbge = cidadeIbge;
    }
}
