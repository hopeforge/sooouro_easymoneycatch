package br.org.graaccto.model.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.graaccto.model.entidades.Configuracao;
import br.org.graaccto.model.entidades.Usuario;
import br.org.graaccto.repository.ConfiguracaoRepository;

@Service
@Transactional
public class ConfiguracaoService {

	@Autowired
	private ConfiguracaoRepository repository;

	public Configuracao salvar(Configuracao configuracao) {
		repository.save(configuracao);
		return configuracao;
	}

	public Configuracao consultar(Usuario empresa) {
		return repository.findByEmpresa(empresa);
	}



}
