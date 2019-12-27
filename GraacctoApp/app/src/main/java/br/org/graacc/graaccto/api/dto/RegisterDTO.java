package br.org.graacc.graaccto.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterDTO implements Serializable {

    private Integer id;
    private String nome;
    private String quantidade;
    private String valor;
    private List<RegisterDTO> detalhes = new ArrayList<>();

    public RegisterDTO() {

    }

    public RegisterDTO(Integer id, String nome, String quantidade, String valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<RegisterDTO> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<RegisterDTO> detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", valor='" + valor + '\'' +
                ", detalhes=" + detalhes +
                '}';
    }
}
