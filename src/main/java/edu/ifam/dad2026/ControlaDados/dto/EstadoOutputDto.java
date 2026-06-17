package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Estado;

public class EstadoOutputDto {

    private String nome;
    private String sigla;
    private String ibge;

    public EstadoOutputDto() {
    }

    public EstadoOutputDto(Estado estado) {
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
        this.ibge = estado.getIbge();
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getIbge() {
        return ibge;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }
}