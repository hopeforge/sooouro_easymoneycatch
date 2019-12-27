package br.org.graacc.graaccto.domain;

import java.io.Serializable;

public class Configuration implements Serializable {

    private Long id;
    private Long idEmpresa;
    private Integer quantidade;
    private Integer tempo;
    private Character ativo;

    public Configuration() {

    }

    public Configuration(Long id, Long idEmpresa, Integer quantidade, Integer tempo, Character ativo) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.quantidade = quantidade;
        this.tempo = tempo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", idEmpresa=" + idEmpresa +
                ", quantidade=" + quantidade +
                ", tempo=" + tempo +
                ", ativo=" + ativo +
                '}';
    }
}
