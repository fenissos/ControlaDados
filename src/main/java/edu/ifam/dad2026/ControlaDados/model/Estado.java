package edu.ifam.dad2026.ControlaDados.model;


public class Estado {

    private String nome;
    private String sigla;
    private String ibge;

    public Estado() {
    }

    public Estado(String nome, String sigla, String ibge) {
        this.nome = nome;
        this.sigla = sigla;
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }
}