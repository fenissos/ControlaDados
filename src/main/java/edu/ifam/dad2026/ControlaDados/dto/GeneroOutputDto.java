package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Genero;

public class GeneroOutputDto {

    private Long id;
    private String nome;

    public GeneroOutputDto() {
    }

    public GeneroOutputDto(Genero genero) {
        this.id = genero.getId();
        this.nome = genero.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
