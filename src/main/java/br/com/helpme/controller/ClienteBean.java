package br.com.helpme.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpme.dao.EstadoDao;
import br.com.helpme.dao.MunicipioDao;
import br.com.helpme.modelo.Estado;
import br.com.helpme.modelo.Municipio;

@Named
@RequestScoped
public class ClienteBean {
	
	private Estado estadoSelecionado;
	
	private List<Estado> listaEstados;
	
	private List<Municipio> listaMunicipios;
	
	@Inject
	private EstadoDao estadoDao;

	@Inject
	private MunicipioDao municipioDao;
	
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

	public void carregarMunicipioPorEstado(){
		if(getEstadoSelecionado()!=null){
			setListaMunicipios(municipioDao.findMunicipiosByEstado(getEstadoSelecionado()));
		}
	}
	

}
