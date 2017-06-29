package br.com.helpme.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpme.dao.EstadoDao;
import br.com.helpme.modelo.Estado;

@Named
@RequestScoped
public class ClienteBean {
	
	private List<Estado> listaEstados;
	
	@Inject
	private EstadoDao estadoDao;

	@PostConstruct
	public void carregarEstados(){
		setListaEstados(estadoDao.carregarEstados());
		System.out.println("carregou os estados");
	}
	
	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	
	

}
