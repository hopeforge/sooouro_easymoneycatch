package br.org.graaccto.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.org.graaccto.model.dto.RegistroDTO;

@Repository
public class ConsultaRepository extends JdbcTemplate {


	@Autowired
	@Qualifier(value="dataSource")
	private DataSource dataSource;

	@PostConstruct
	public void init(){
		setDataSource(dataSource);
	}



		public List<RegistroDTO> listar(Integer idEmpresa){
			String sql =  "select grato.id, usuario.nome, count(grato.id)  quantidade, sum(grato.valor) valor"
					+ " from "
					+ " tb_grato grato,"
					+ " tb_usuario usuario"
					+ " where"
					+ " grato.idusuario = usuario.id"
					+ " and grato.iddoacao is null"
					+ " and grato.valor is null"
					+ " and grato.idempresa = " + idEmpresa
					+ " group by grato.id, usuario.nome";

			return query(sql, new RegistroDTOMapper());
		}

		public List<RegistroDTO> listarDebitos(Integer idEmpresa){
			String sql =  "select doacao.id, '' as nome, '' as quantidade,  sum(doacao.valor) valor"
					+ " from "
					+ " tb_doacao doacao,"
					+ " tb_usuario usuario"
					+ " where"
					+ " doacao.idusuario = usuario.id"
					+ " and doacao.valor is not null"
					+ " and doacao.pagamento = 0"
					+ " and doacao.idempresa = " + idEmpresa
					+ " group by doacao.id";

			return query(sql, new RegistroDTOMapper());
		}



		public List<RegistroDTO> listarUsuarioDoacao(Integer idUsuario) {

			 String sql = "select usuario.id, usuario.nome, count(doacao.id) quantidade, sum(doacao.valor) valor"
						+ " from "
						+ " tb_doacao doacao,"
						+ " tb_usuario usuario"
						+ " where"
						+ " doacao.idempresa = usuario.id"
						+ " and doacao.idusuario = " + idUsuario
						+ " group by usuario.id, usuario.nome";

			 return query(sql, new RegistroDTOMapper());
		}


		public List<RegistroDTO> listarEmpresaDoacao(Integer idUsuario){
			String sql = "select usuario.id, usuario.nome, count(doacao.id) quantidade, sum(doacao.valor) valor"
					+ " from "
					+ " tb_doacao doacao,"
					+ " tb_usuario usuario"
					+ " where"
					+ " doacao.idusuario = usuario.id"
					+ " and doacao.idempresa = " + idUsuario
					+ " group by usuario.id, usuario.nome";

			return query(sql, new RegistroDTOMapper());

		}



		public List<RegistroDTO> listarUsuario(Integer idUsuario){
			String sql = "select usuario.id, usuario.nome, count(grato.id) quantidade, sum(grato.valor) valor"
					+ " from "
					+ " tb_grato grato,"
					+ " tb_usuario usuario"
					+ " where"
					+ " grato.idusuario = usuario.id"
					+ " and grato.iddoacao is null"
					+ " and grato.idUsuario = " + idUsuario
					+ " group by usuario.id, usuario.nome";
			return query(sql, new RegistroDTOMapper());
		}

		public List<RegistroDTO> listarUsuarioDetalhe(Integer idEmpresa){

			String sql = "select grato.id, usuario.nome, grato.data, grato.valor"
					+ " from "
					+ " tb_grato grato,"
					+ " tb_usuario usuario"
					+ " where"
					+ " grato.idusuario = usuario.id"
					+ " and grato.iddoacao is null"
					+ " and grato.idempresa = " + idEmpresa
					+ " group by usuario.id, usuario.nome";

			return query(sql, new RegistroDTOMapper());

		}


	public List<RegistroDTO> listarEmpresa(Integer idUsuario){
			String sql =  "select usuario.id, usuario.nome, count(grato.id) as quantidade, sum(grato.valor) as valor"
					+ " from "
					+ " tb_grato grato,"
					+ " tb_usuario usuario"
					+ " where"
					+ " grato.idusuario = usuario.id"
					+ " and grato.iddoacao is null"
					+ " and grato.idempresa = " + idUsuario
					+ " group by usuario.id, usuario.nome";

			return query(sql, new RegistroDTOMapper());

	}


	private class RegistroDTOMapper implements RowMapper<RegistroDTO> {


		public RegistroDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			RegistroDTO registro = new RegistroDTO();

			registro.setId(rs.getInt("id"));
			registro.setNome(rs.getString("nome"));
			registro.setQuantidade(rs.getString("quantidade"));
			Double valor = rs.getDouble("valor");

			if (valor != 0.0) {
				registro.setValor(valor);
			}

			return registro;
		}
	}

}
