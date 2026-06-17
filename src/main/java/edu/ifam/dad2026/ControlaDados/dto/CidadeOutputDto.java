package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Cidade;
import edu.ifam.dad2026.ControlaDados.model.Estado;

public class CidadeOutputDto {

    private Long id;
    private String nome;
    private String ibge;
    private Estado estado;

    public CidadeOutputDto() {
    }

    public CidadeOutputDto(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.ibge = cidade.getIbge();
        this.estado = cidade.getEstado();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIbge() {
        return ibge;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}