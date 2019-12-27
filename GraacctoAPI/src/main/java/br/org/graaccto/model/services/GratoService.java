package br.org.graaccto.model.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Grato;
import br.org.graaccto.repository.ConsultaRepository;
import br.org.graaccto.repository.GratoRepository;

@Service
@Transactional
public class GratoService {


	@Autowired
	private GratoRepository gratoRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	public Grato salvar(Grato grato) {
		gratoRepository.save(grato);
		return grato;
	}

	public Grato atualizarValor(Integer id, Double valor) {
		Grato grato = gratoRepository.findById(id);
		grato.setValor(valor);
		gratoRepository.save(grato);
		return grato;
	}


	public List<RegistroDTO> listar(Integer idEmpresa){
		return consultaRepository.listar(idEmpresa);
	}

	public List<RegistroDTO> listarEmpresa(Integer idEmpresa){

		return consultaRepository.listarEmpresa(idEmpresa);
//		List<RegistroDTO> listaDetalhe = gratoRepository.listarEmpresaDetalhe(idUsuario);
//
//		return merge(lista, listaDetalhe);

	}

	public List<RegistroDTO> listarUsuario(Integer idUsuario){

		return  consultaRepository.listarUsuario(idUsuario);
//		List<RegistroDTO> listaDetalhe = gratoRepository.listarUsuarioDetalhe(idEmpresa);
//
//		return merge(lista, listaDetalhe);

	}


	private List<RegistroDTO> merge(List<RegistroDTO> lista, List<RegistroDTO> listaDetalhe){

		Map<Integer, RegistroDTO> mapa = new HashMap<Integer, RegistroDTO>();
		List<RegistroDTO> listaEnvio = new ArrayList<RegistroDTO>();

		for (RegistroDTO registro: lista) {

			mapa.put(registro.getId(), registro);
			listaEnvio.add(registro);

		}

		RegistroDTO reg;
		for (RegistroDTO detalhe:  listaDetalhe) {
			reg = mapa.get(detalhe.getId());
			if (reg != null) {
			//	reg.getDetalhes().add(reg);
			}
		}

		return listaEnvio;

	}




}
