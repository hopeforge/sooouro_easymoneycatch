package br.org.graaccto.controller;

import java.util.List;

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

import br.org.graaccto.model.dto.CodigoBarraDTO;
import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Doacao;
import br.org.graaccto.model.services.DoacaoService;

@RestController
@CrossOrigin("*")
@RequestMapping("doacao")
public class DoacaoController {

	@Autowired
	private DoacaoService service;


	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Doacao> salvar(@RequestBody Doacao doacao) throws Exception {
		return new ResponseEntity<Doacao>(service.salvar(doacao), HttpStatus.OK);
	}

	@RequestMapping(value="gerarPagamento", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<CodigoBarraDTO> gerarPagamento(@RequestParam(value = "id" , required = true) Integer id) throws Exception {

		return new ResponseEntity<CodigoBarraDTO>(service.gerarCodigoBarra(id), HttpStatus.OK);

	}

	@RequestMapping(value="listarEmpresa", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RegistroDTO>> listarUsuario(@RequestParam(value = "id" , required = true) Integer id) throws Exception {

		return new ResponseEntity<List<RegistroDTO>>(service.listarEmpresa(id), HttpStatus.OK);
	}

	@RequestMapping(value="listarUsuario", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RegistroDTO>> listarEmpresa(@RequestParam(value = "id" , required = true) Integer id) throws Exception {
		return new ResponseEntity<List<RegistroDTO>>(service.listarUsuario(id), HttpStatus.OK);
	}




}
