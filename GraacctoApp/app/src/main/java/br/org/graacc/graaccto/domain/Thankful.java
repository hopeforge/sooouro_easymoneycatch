package br.org.graacc.graaccto.domain;

import java.io.Serializable;

public class Thankful implements Serializable {

    private Integer id;
    private User usuario;
    private User empresa;
    private Double valor;
    private Integer situacao;
    private Donation doacao;
    private String data;

    public Thankful() {

    }

    public Thankful(Integer id, User usuario, User empresa, Double valor, Integer situacao, Donation doacao, String data) {
        this.id = id;
        this.usuario = usuario;
        this.empresa = empresa;
        this.valor = valor;
        this.situacao = situacao;
        this.doacao = doacao;
        this.data = data;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public User getEmpresa() {
        return empresa;
    }

    public void setEmpresa(User empresa) {
        this.empresa = empresa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Donation getDoacao() {
        return doacao;
    }

    public void setDoacao(Donation doacao) {
        this.doacao = doacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Thankful{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", empresa=" + empresa +
                ", valor=" + valor +
                ", situacao=" + situacao +
                ", doacao=" + doacao +
                ", data='" + data + '\'' +
                '}';
    }
}
