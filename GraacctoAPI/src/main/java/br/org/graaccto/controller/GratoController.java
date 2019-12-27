package br.org.graaccto.controller;

import java.util.List;

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

import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Grato;
import br.org.graaccto.model.services.GratoService;

@RestController
@CrossOrigin("*")
@RequestMapping("grato")
public class GratoController {

	@Autowired
	private GratoService service;

	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Grato> salvar(@RequestBody Grato grato) throws Exception {
		return new ResponseEntity<Grato>(service.salvar(grato), HttpStatus.OK);
	}

	@RequestMapping(value="listarEmpresa", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RegistroDTO>> listarEmpresa (@RequestParam(value = "id" , required = true) Integer id) throws Exception {
		return new ResponseEntity<List<RegistroDTO>>(service.listarEmpresa(id), HttpStatus.OK);
	}

	@RequestMapping(value="listarUsuario", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RegistroDTO>> listarUsuario(@RequestParam(value = "id" , required = true) Integer id) throws Exception {
		return new ResponseEntity<List<RegistroDTO>>(service.listarUsuario(id), HttpStatus.OK);
	}

	@RequestMapping(value="listar", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RegistroDTO>> listar(	@RequestParam(value = "id" , required = true) Integer id) throws Exception {
		List<RegistroDTO> lista = service.listar(id);
		return new ResponseEntity<List<RegistroDTO>>(lista, HttpStatus.OK);
	}

	@RequestMapping(value="atualizarValor", method = {RequestMethod.GET})
	public @ResponseBody ResponseEntity<Grato> atualizarValor(
			@RequestParam(value = "id" , required = true) Integer id,
			@RequestParam(value = "valor" , required = true) Double valor,
			HttpServletRequest request) throws Exception {

		return new ResponseEntity<Grato>(service.atualizarValor(id, valor), HttpStatus.OK);

	}







}
