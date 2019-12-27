package br.org.graaccto.model.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_grato")
public class Grato implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "IDUSUARIO", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "IDEMPRESA", nullable = false)
	private Usuario empresa;

	@Column(name = "data", nullable = true)
	private Date data;

	@Column(name = "valor", nullable = true)
	private Double valor;

	@Column(name = "situacao", nullable = false)
	private Integer situacao;

	@ManyToOne
	@JoinColumn(name = "IDDOACAO", nullable = true)
	private Doacao doacao;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Usuario empresa) {
		this.empresa = empresa;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	public Doacao getDoacao() {
		return doacao;
	}
	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}


}
