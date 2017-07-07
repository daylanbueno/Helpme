package br.com.helpme.dao;

import java.util.List;


import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.helpme.modelo.Categoria;

public class CategoriaDao {

	@Inject
	private EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	public List<Categoria> findAllCategoria(){
		String jpql= null;
		jpql =" from Categoria";
		return manager.createQuery(jpql).getResultList();
		
	}
}
