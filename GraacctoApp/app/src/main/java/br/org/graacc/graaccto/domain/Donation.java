package br.org.graacc.graaccto.domain;

import java.io.Serializable;
import java.util.Date;

public class Donation implements Serializable {

    private Integer id;
    private User usuario;
    private User empresa;
    private Double valor;
    private String mensagem;
    private String data;
    private Integer pagamento;

    public Donation() {

    }

    public Donation(Integer id, User usuario, User empresa, Double valor, String mensagem, String data, Integer pagamento) {
        this.id = id;
        this.usuario = usuario;
        this.empresa = empresa;
        this.valor = valor;
        this.mensagem = mensagem;
        this.data = data;
        this.pagamento = pagamento;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPagamento() {
        return pagamento;
    }

    public void setPagamento(Integer pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", empresa=" + empresa +
                ", valor=" + valor +
                ", mensagem='" + mensagem + '\'' +
                ", data='" + data + '\'' +
                ", pagamento=" + pagamento +
                '}';
    }
}
