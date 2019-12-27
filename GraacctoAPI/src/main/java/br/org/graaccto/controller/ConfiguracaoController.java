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

import br.org.graaccto.model.entidades.Configuracao;
import br.org.graaccto.model.entidades.Usuario;
import br.org.graaccto.model.services.ConfiguracaoService;
import br.org.graaccto.util.http.GraaccApiResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("configuracao")
public class ConfiguracaoController {

	@Autowired
	private ConfiguracaoService service;

	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Configuracao> salvar(@RequestBody Configuracao configuracao) throws Exception {
		return new ResponseEntity<Configuracao>(service.salvar(configuracao), HttpStatus.OK);
	}

	@RequestMapping(value="consultar", method = {RequestMethod.GET})
	public GraaccApiResponse<Configuracao> consultar(
			@RequestParam(value = "id" , required = true) String id,
			HttpServletRequest request) throws Exception {

		Usuario usuario = new Usuario();
		usuario.setId(new Integer(id));

		Configuracao conf = service.consultar(usuario);

		return GraaccApiResponse.withBody(conf);
	}



}
