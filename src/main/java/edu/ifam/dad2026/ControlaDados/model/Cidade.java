package edu.ifam.dad2026.ControlaDados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String ibge;

    @Column(nullable = false)
    private String estadoIbge;

    public Cidade() {
    }

    public Cidade(String nome, String ibge, String estadoIbge) {
        this.nome = nome;
        this.ibge = ibge;
        this.estadoIbge = estadoIbge;
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

    public String getEstadoIbge() {
        return estadoIbge;
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

    public void setEstadoIbge(String estadoIbge) {
        this.estadoIbge = estadoIbge;
    }
}