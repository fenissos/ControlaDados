package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Cidade;

public class CidadeOutputDto {

    private Long id;
    private String nome;
    private String ibge;
    private EstadoOutputDto estado;

    public CidadeOutputDto() {
    }

    public CidadeOutputDto(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.ibge = cidade.getIbge();
        this.estado = new EstadoOutputDto(cidade.getEstado());
    }

    public CidadeOutputDto(Cidade cidade, EstadoOutputDto estado) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.ibge = cidade.getIbge();
        this.estado = estado;
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

    public EstadoOutputDto getEstado() {
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

    public void setEstado(EstadoOutputDto estado) {
        this.estado = estado;
    }
}
