package br.org.graaccto.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_configuracao")
public class Configuracao implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "IDEMPRESA", nullable = false)
	private Usuario empresa;

	@Column(name = "quantidade", nullable = true)
	private Integer quantidade;

	@Column(name = "tempo", nullable = true)
	private Integer tempo;

	@Column(name = "fidelidade", nullable = true)
	private Integer fidelidade;
	
	@Column(name = "porcentagem", nullable = true)
	private Integer porcentagem;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Usuario empresa) {
		this.empresa = empresa;
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
	public Integer getFidelidade() {
		return fidelidade;
	}
	public void setFidelidade(Integer fidelidade) {
		this.fidelidade = fidelidade;
	}

}
