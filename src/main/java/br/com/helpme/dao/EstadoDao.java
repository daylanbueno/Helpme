package br.com.helpme.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.com.helpme.modelo.Estado;

public class EstadoDao {

	@Inject
	private EntityManager manager;
		
	public List<Estado> carregarEstados(){
		return manager.createQuery("from Estado",Estado.class).getResultList();
	}
	
}
