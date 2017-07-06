package br.com.helpme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.helpme.dao.EstadoDao;
import br.com.helpme.modelo.Estado;

@Named
@ApplicationScoped
public class ParceitoBean {

	
	@Inject
	private EstadoDao estadoDao;
	
	private Estado estadoSelecionado;
	
	private List<Estado> estados;
	
	
	@PostConstruct
	public void carregarEstados(){
		setEstados(estadoDao.carregarEstados());
	}


	public List<Estado> getEstados() {
		if(estados == null){
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
	
	

	
}
