package br.com.helpme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.helpme.dao.CategoriaDao;
import br.com.helpme.dao.EstadoDao;
import br.com.helpme.dao.MunicipioDao;
import br.com.helpme.modelo.Categoria;
import br.com.helpme.modelo.Estado;
import br.com.helpme.modelo.Municipio;
import br.com.helpme.modelo.PessoaJuridica;

@Named
@ApplicationScoped
public class ParceiroBean {

	@Inject
	private EstadoDao estadoDao;

	@Inject
	private MunicipioDao municipiodao;

	@Inject
	private CategoriaDao categoriadao;

	private Estado estadoSelecionado;

	private List<Estado> estados;

	private List<Categoria> listaCategoria;

	@Inject
	private PessoaJuridica pessoaJuridica;

	@PostConstruct
	public void carregarEstados() {
		setEstados(estadoDao.carregarEstados());
		setListaCategoria(categoriadao.findAllCategoria());
	}

	public List<Estado> getEstados() {
		if (estados == null) {
			estados = new ArrayList<>();
		}
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public List<Categoria> getListaCategoria() {
		if (listaCategoria == null)
			listaCategoria = new ArrayList<>();
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public PessoaJuridica getPessoaJuridica() {
		if (pessoaJuridica == null) {
			pessoaJuridica = new PessoaJuridica();
		}
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public List<Municipio> carregarMunicipio() {
		List<Municipio> listaMunicipios = new ArrayList<>();
		if (getEstadoSelecionado() != null) {
			listaMunicipios = municipiodao.findMunicipiosByEstado(getEstadoSelecionado());
		}
		return listaMunicipios;
	}

	public void salvar() {
		if (getPessoaJuridica() != null && isCnpjValido()) {
				System.out.println("Passo para salvar");
		}

	}

	public boolean isCnpjValido() {

		String cnpj = null;
		if (getPessoaJuridica() != null) {
			cnpj = getPessoaJuridica().getCnpj();
		}

		if (!cnpj.substring(0, 1).equals("")) {
			try {
				cnpj = cnpj.replace('.', ' ');// onde há ponto coloca espaço
				cnpj = cnpj.replace('/', ' ');// onde há barra coloca espaço
				cnpj = cnpj.replace('-', ' ');// onde há traço coloca espaço
				cnpj = cnpj.replaceAll(" ", "");// retira espaço
				int soma = 0, dig;
				String cnpj_calc = cnpj.substring(0, 12);
				if (cnpj.length() != 14) {
					return false;
				}
				char[] chr_cnpj = cnpj.toCharArray();
				/* Primeira parte */
				for (int i = 0; i < 4; i++) {
					if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
						soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
					}
				}
				for (int i = 0; i < 8; i++) {
					if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
						soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
					}
				}
				dig = 11 - (soma % 11);
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
				/* Segunda parte */
				soma = 0;
				for (int i = 0; i < 5; i++) {
					if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
						soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
					}
				}
				for (int i = 0; i < 8; i++) {
					if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
						soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
					}
				}
				dig = 11 - (soma % 11);
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
				
				boolean isValido = cnpj.equals(cnpj_calc);
				if(!isValido){
					Messages.addGlobalError("CNPJ Invalido!");
				}
				return isValido;
			} catch (Exception e) {
				Messages.addGlobalError("CNPJ Invalido!");
				return false;
			}
		} else {
			Messages.addGlobalError("CNPJ Invalido!");
			return false;
		}
	}

}
