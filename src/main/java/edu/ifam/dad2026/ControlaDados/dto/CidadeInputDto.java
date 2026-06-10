package edu.ifam.dad2026.ControlaDados.dto;

import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.model.Cidade;
import edu.ifam.dad2026.ControlaDados.model.Estado;

public class CidadeInputDto {

    private String nome;
    private String ibge;
    private Long estadoId;

    private EstadoRepository estadoRepository;

    public CidadeInputDto() {
    }

    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Cidade build() {

        Estado estado = estadoRepository.findById(estadoId).get();

        return new Cidade(
                nome,
                ibge,
                estado
        );
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
}