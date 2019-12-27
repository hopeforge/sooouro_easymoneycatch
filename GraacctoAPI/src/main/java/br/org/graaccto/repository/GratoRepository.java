package br.org.graaccto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Grato;

@Repository
public interface GratoRepository extends GerericRepository<Grato> {

	public Grato findById(Integer id);



	@Query(value = "select usuario.id, usuario.nome, count(grato.id)  quantidade, sum(grato.valor) valor"
			+ " from "
			+ " tb_grato grato,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and grato.iddoacao is null"
			+ " and grato.valor is null"
			+ " and grato.idempresa = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listar(Integer idEmpresa);



	@Query(value = "select usuario.id, usuario.nome, count(grato.id), sum(grato.valor)"
		+ " from "
		+ " tb_grato grato,"
		+ " tb_usuario usuario"
		+ " where"
		+ " grato.idusuario = usuario.id"
		+ " and grato.iddoacao is null"
		+ " and grato.idempresa = ?1"
		+ " group by usuario.id, usuario.nome",  nativeQuery = true)
	public List<RegistroDTO> listarUsuario(Integer idEmpresa);

	@Query(value = "select grato.id, usuario.nome, grato.data, grato.valor"
			+ " from "
			+ " tb_grato grato,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and grato.iddoacao is null"
			+ " and grato.idempresa = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarUsuarioDetalhe(Integer idEmpresa);


	@Query(value = "select usuario.id, usuario.nome, count(grato.id) as quantidade, sum(grato.valor)"
			+ " from "
			+ " tb_grato grato,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and grato.iddoacao is null"
			+ " and grato.idusuario = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarEmpresa(Integer idUsuario);

	@Query(value = "select grato.id, usuario.nome, grato.data, grato.valor"
			+ " from "
			+ " tb_grato grato,"
			+ " tb_usuario usuario"
			+ " where"
			+ " grato.idusuario = usuario.id"
			+ " and grato.iddoacao is null"
			+ " and grato.idusuario = ?1"
			+ " group by usuario.id, usuario.nome",  nativeQuery = true)
		public List<RegistroDTO> listarEmpresaDetalhe(Integer idUsuario);

}
