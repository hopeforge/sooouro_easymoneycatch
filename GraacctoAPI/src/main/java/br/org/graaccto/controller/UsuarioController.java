package br.org.graaccto.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.org.graaccto.model.entidades.Usuario;
import br.org.graaccto.model.services.UsuarioService;
import br.org.graaccto.util.http.GraaccApiResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;


	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) throws Exception {
		return new ResponseEntity<Usuario>(service.salvar(usuario), HttpStatus.OK);
	}

	@RequestMapping(value="consultar", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Usuario> consultar(@RequestParam(value = "id" , required = true) String id) throws Exception {
		return new ResponseEntity<Usuario>(service.consultar(new Integer(id)), HttpStatus.OK);
	}

	@RequestMapping(value="pesquisar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Usuario> pesquisar(@RequestBody Usuario usuario) throws Exception {
		return new ResponseEntity<Usuario>(service.pesquisar(usuario.getEmail()), HttpStatus.OK);
	}

	@RequestMapping(value="logar", method = {RequestMethod.GET})
	public GraaccApiResponse<Usuario> logar(
			@RequestParam(value = "login" , required = true) String login,
			@RequestParam(value = "senha" , required = true) String senha,
			HttpServletRequest request) throws Exception {

		Usuario usuario = service.logar(login, senha);

		return GraaccApiResponse.withBody(usuario);
	}

	@RequestMapping(value="logarApp", method = {RequestMethod.GET})
	public ResponseEntity<Usuario> logarApp(
			@RequestParam(value = "login" , required = true) String login,
			@RequestParam(value = "senha" , required = true) String senha,
			HttpServletRequest request) throws Exception {

		Usuario usuario = service.logar(login, senha);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}





}
