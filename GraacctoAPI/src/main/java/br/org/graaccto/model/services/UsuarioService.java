package br.org.graaccto.model.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.graaccto.model.entidades.Usuario;
import br.org.graaccto.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}

	public Usuario logar(String login, String senha) {

		return usuarioRepository.findFirstUsuarioByLoginAndSenha(login, senha);
	}

	public Usuario pesquisar(String email) {

		return usuarioRepository.findFirstUsuarioByEmail(email);
	}

	public Usuario consultar(Integer id) {
		return usuarioRepository.findFirstUsuarioById(id);
	}

}
