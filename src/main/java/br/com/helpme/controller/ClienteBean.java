package br.com.helpme.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EnumType;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import br.com.helpme.dao.ClienteDao;
import br.com.helpme.dao.EstadoDao;
import br.com.helpme.dao.MunicipioDao;
import br.com.helpme.modelo.Estado;
import br.com.helpme.modelo.Municipio;
import br.com.helpme.modelo.PessoaFisica;
import br.com.helpme.modelo.TipoPessoa;

@Named
@ApplicationScoped
public class ClienteBean {
	
	private Estado estadoSelecionado;
	
	private Municipio municipioSelecionado;
	
	private List<Estado> listaEstados;
	
	private List<Municipio> listaMunicipios;
	
	@Inject
	private EstadoDao estadoDao;

	@Inject
	private MunicipioDao municipioDao;
	
	@Inject
	private ClienteDao clientedao;
	
	@Inject
	private PessoaFisica pessoaFisica;
	
	@PostConstruct
	public void carregarEstados(){
		setListaEstados(estadoDao.carregarEstados());
		System.out.println("carregou os estados=>"+getEstadoSelecionado().getNome());
	}
	
	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public Estado getEstadoSelecionado() {
		if(estadoSelecionado== null){
			estadoSelecionado = new Estado();
		}
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	
	
	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}
	

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	
	public Municipio getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(Municipio municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}

	public void carregarMunicipioPorEstado(){
		if(getEstadoSelecionado()!=null){
			setListaMunicipios(municipioDao.findMunicipiosByEstado(getEstadoSelecionado()));
		}
	}

	public Date getDataHoje(){
		return new Date();
	}

	public void salvarCliente(){
		if(getPessoaFisica()!=null){
			getPessoaFisica().getPessoa().setTipoPessoa(TipoPessoa.FUSICA);
			clientedao.salvarCliente(getPessoaFisica());
			Messages.addGlobalInfo("Operação realizada com sucesso");;
		}
	}
	


}
