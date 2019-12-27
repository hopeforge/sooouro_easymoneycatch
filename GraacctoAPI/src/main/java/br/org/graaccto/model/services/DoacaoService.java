package br.org.graaccto.model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.graaccto.model.dto.CodigoBarraDTO;
import br.org.graaccto.model.dto.DoacaoDTO;
import br.org.graaccto.model.dto.RegistroDTO;
import br.org.graaccto.model.entidades.Doacao;
import br.org.graaccto.model.entidades.Usuario;
import br.org.graaccto.repository.ConsultaRepository;
import br.org.graaccto.repository.DoacaoRepository;

@Service
@Transactional
public class DoacaoService {

	@Autowired
	private DoacaoRepository repository;
	@Autowired
	private ConsultaRepository consultaRepository;

	public Doacao salvar(Doacao doacao) {

//		List<RegistroDTO> lista = consultaRepository.listarUsuario(doacao.getId());
//
//		if (lista != null && !lista.isEmpty()) {

			Usuario empresa = new Usuario();
			empresa.setId(2);
			doacao.setEmpresa(empresa);
			doacao.setValor(17.4);
			doacao.setData(new Date());
			doacao.setPagamento(0);
			repository.save(doacao);
//		}


		return doacao;
	}

	public CodigoBarraDTO gerarCodigoBarra(Integer id) {

		List<RegistroDTO> lista = consultaRepository.listarDebitos(id);

		CodigoBarraDTO codigo = new CodigoBarraDTO();
		 Random gerador = new Random();

		String barra = "";

		if (!lista.isEmpty()) {
			codigo.setValor(String.valueOf(lista.get(0).getValor()));



			//imprime sequência de 10 números inteiros aleatórios
	        for (int i = 1; barra.length() < 30; i++) {

	        	if (i%9 == 0) {
	        		barra += " ";
	        	}

	        	int digito = gerador.nextInt();
	        	if (digito >= 0) {
	        		barra += digito;
	        	}

	        }


		}
		codigo.setCodigoBarra(barra);

		return codigo;
	}



	public Doacao listarDoacao(Integer idUsuario) {

		List<DoacaoDTO> lista = repository.listarDoacao(idUsuario);

		Doacao doacaoEnvio = new Doacao();

		Double total = 0.0;
		Integer idEmpresa = 0;
		Integer idUsu= 0;
		for (DoacaoDTO doacao: lista) {
			if (dataValidada(doacao.getData(), doacao.getQuantConfiguracao())) {
				total += doacao.getValor();
				idEmpresa = doacao.getIdEmpresa();
				idUsu = doacao.getIdUsuario();
			}
		}

		doacaoEnvio.setValor(total);
		Usuario empresa = new Usuario();
		empresa.setId(idEmpresa);
		doacaoEnvio.setEmpresa(empresa);

		Usuario usu = new Usuario();
		usu.setId(idUsu);
		doacaoEnvio.setEmpresa(usu);

		return doacaoEnvio;

	}

	public List<RegistroDTO> listarEmpresa(Integer idEmpresa){

		return consultaRepository.listarEmpresaDoacao(idEmpresa);
	//	List<RegistroDTO> listaDetalhe = repository.listarEmpresaDetalhe(idUsuario);

	//	return merge(lista, listaDetalhe);

	}

	public List<RegistroDTO> listarUsuario(Integer idUsuario){

		List<RegistroDTO> lista = consultaRepository.listarUsuarioDoacao(idUsuario);
		return lista;
//		List<RegistroDTO> listaDetalhe = repository.listarUsuarioDetalhe(idEmpresa);
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
//				reg.getDetalhes().add(reg);
			}
		}

		return listaEnvio;

	}


	  public boolean dataValidada(Date data,  int valor) {


	        Calendar calendar = GregorianCalendar.getInstance();

	        calendar.setTime(data);
	        calendar.add(Calendar.DAY_OF_MONTH, valor);
	        Date dataRegistro = calendar.getTime();

	        return dataRegistro.after(new Date());

	    }




}
