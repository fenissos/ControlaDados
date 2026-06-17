package edu.ifam.dad2026.ControlaDados.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String email;

    @Column(nullable = false)
    private String cidadeIbge;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email, String cidadeIbge) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cidadeIbge = cidadeIbge;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getCidadeIbge() {
        return cidadeIbge;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCidadeIbge(String cidadeIbge) {
        this.cidadeIbge = cidadeIbge;
    }
}