package br.com.helpme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EnumType;

import org.hibernate.annotations.GenerationTime;
import org.omnifaces.util.Messages;

import br.com.helpme.dao.CategoriaDao;
import br.com.helpme.dao.EstadoDao;
import br.com.helpme.dao.MunicipioDao;
import br.com.helpme.dao.ParceiroDao;
import br.com.helpme.modelo.Categoria;
import br.com.helpme.modelo.Estado;
import br.com.helpme.modelo.Municipio;
import br.com.helpme.modelo.PessoaJuridica;
import br.com.helpme.modelo.TipoPessoa;
import br.com.helpme.validador.ValidaCNPJ;

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
	
	@Inject
	private ValidaCNPJ validarCnpj;
	
	private List<Categoria> listaCategoria;

	@Inject
	private PessoaJuridica pessoaJuridica;
	
	@Inject
	private ParceiroDao parceirodao;

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
			try {
				getPessoaJuridica().getPessoa().setTipoPessoa(TipoPessoa.JURICIA);
				parceirodao.salvarParceido(getPessoaJuridica());
				Messages.addGlobalInfo("Operação efetuada com sucesso");
			} catch (Exception e) {
				Messages.addGlobalError("Erro ao salvar");
			}
		}

	}

	public boolean isCnpjValido() {
		boolean isValido = false;
		if(getPessoaJuridica()!=null){
		   isValido	= validarCnpj.isCnpjValido(getPessoaJuridica().getCnpj());
		}
		if(!isValido){
			Messages.addGlobalError("CNPJ Invalido!");
		}
		return isValido;
	}

	
}
