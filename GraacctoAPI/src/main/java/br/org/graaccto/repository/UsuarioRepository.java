package br.org.graaccto.repository;

import org.springframework.stereotype.Repository;

import br.org.graaccto.model.entidades.Usuario;

@Repository
public interface UsuarioRepository extends GerericRepository<Usuario> {


	public Usuario findFirstUsuarioByLoginAndSenha(String login, String senha);
	public Usuario findFirstUsuarioByEmail(String email);
	public Usuario findFirstUsuarioById(Integer id);

}
