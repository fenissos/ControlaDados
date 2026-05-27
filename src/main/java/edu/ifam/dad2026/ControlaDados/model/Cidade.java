package edu.ifam.dad2026.ControlaDados.model;

public class Cidade {

    private String nome;
    private String ibge;
    private String estado;

    public Cidade(){
    }

    public Cidade(String nome, String ibge, String estado) {
        this.nome = nome;
        this.ibge = ibge;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
