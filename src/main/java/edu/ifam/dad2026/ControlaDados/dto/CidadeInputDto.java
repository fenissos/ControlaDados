package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Cidade;

public class CidadeInputDto {

    private String nome;
    private String ibge;
    private String estadoIbge;

    public CidadeInputDto() {
    }

    public Cidade build() {
        return new Cidade(nome, ibge, estadoIbge);
    }

    public String getNome() {
        return nome;
    }

    public String getIbge() {
        return ibge;
    }

    public String getEstadoIbge() {
        return estadoIbge;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public void setEstadoIbge(String estadoIbge) {
        this.estadoIbge = estadoIbge;
    }
}