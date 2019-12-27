package br.org.graaccto.model.dto;

import java.util.Date;

public class DoacaoDTO {

	private Integer idEmpresa;
	private Integer idUsuario;
	private Integer quantConfiguracao;
	private Double valor;
	private Integer quantidade;
	private Integer tempo;
	private Date data;




	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getQuantConfiguracao() {
		return quantConfiguracao;
	}
	public void setQuantConfiguracao(Integer quantConfiguracao) {
		this.quantConfiguracao = quantConfiguracao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

}
