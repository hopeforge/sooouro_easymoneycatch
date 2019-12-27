package br.org.graaccto.repository;

import br.org.graaccto.model.entidades.Configuracao;
import br.org.graaccto.model.entidades.Usuario;

public interface ConfiguracaoRepository extends GerericRepository<Configuracao> {


	public Configuracao findByEmpresa(Usuario empresa);


}
