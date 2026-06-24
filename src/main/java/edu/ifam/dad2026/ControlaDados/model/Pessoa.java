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

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email, Genero genero, Cidade cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.genero = genero;
        this.cidade = cidade;
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

    public Genero getGenero() {
        return genero;
    }

    public Cidade getCidade() {
        return cidade;
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

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
