package br.org.graaccto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.org.graaccto.model.dto.DoacaoDTO;
import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Doacao;

public interface DoacaoRepository extends GerericRepository<Doacao> {



	@Query(value = "select usuario.id, usuario.nome, count(doacao.id), sum(doacao.valor)"
		+ " from "
		+ " tb_doacao doacao,"
		+ " tb_usuario usuario"
		+ " where"
		+ " doacao.idusuario = usuario.id"
		+ " and doacao.idempresa = ?1"
		+ " group by usuario.id, usuario.nome",  nativeQuery = true)
	public List<RegistroDTO> listarUsuario(Integer idEmpresa);

	@Query(value = "select grato.id, usuario.nome, doacao.data, doacao.valor"
			+ " from "
			+ " tb_doacao doacao,"
			+ " tb_usuario usuario"
			+ " where"
			+ " doacao.idusuario = usuario.id"
			+ " and doacao.idempresa = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarUsuarioDetalhe(Integer idEmpresa);


	@Query(value = "select usuario.id, usuario.nome, count(doacao.id), sum(doacao.valor)"
			+ " from "
			+ " tb_doacao doacao,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and doacao.idusuario = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarEmpresa(Integer idUsuario);

	@Query(value = "select grato.id, usuario.nome, doacao.data, doacao.valor"
			+ " from "
			+ " tb_doacao doacao,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and doacao.idusuario = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarEmpresaDetalhe(Integer idUsuario);


	@Query(value = "select grato.idempresa as id, conf.quantidade as quantConfiguracao, conf.tempo,"
			+ " grato.data,"
			+ " count(grato.idempresa) as quantidade, sum(grato.valor) as valor"
			+ " from "
			+ " tb_grato grato, "
			+ " tb_configuracao conf"
			+ " where "
			+ " grato.idempresa = conf.idempresa"
			+ " and grato.idusuario = ?1"
			+ " group by grato.idempresa, conf.quantidade, conf.tempo, grato.data"
			+ " having count(grato.idempresa)>= conf.quantidade",  nativeQuery = true)
	public List<DoacaoDTO> listarDoacao(Integer idUsuario);

}
