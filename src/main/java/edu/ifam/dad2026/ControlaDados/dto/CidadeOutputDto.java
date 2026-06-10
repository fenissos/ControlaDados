package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Cidade;

public class CidadeOutputDto {

    private Long id;
    private String nome;
    private String ibge;
    private Long estadoId;
    private String estadoNome;

    public CidadeOutputDto() {
    }

    public CidadeOutputDto(Cidade cidade) {

        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.ibge = cidade.getIbge();

        if (cidade.getEstado() != null) {
            this.estadoId = cidade.getEstado().getId();
            this.estadoNome = cidade.getEstado().getNome();
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

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
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