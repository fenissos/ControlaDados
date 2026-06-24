package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.model.Genero;

public class GeneroInputDto {

    private String nome;

    public GeneroInputDto() {
    }

    public Genero build() {
        return new Genero(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
