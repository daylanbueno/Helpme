package br.com.helpme.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import br.com.helpme.modelo.PessoaJuridica;

public class ParceiroDao {

	@Inject
	private EntityManager manager;
	
	
	public void salvarParceido(PessoaJuridica parceito){
		
		EntityTransaction txt = manager.getTransaction();
		
		try {
			txt.begin();
			manager.persist(parceito.getPessoa().getEndereco());
			manager.persist(parceito.getPessoa().getContato());
			manager.persist(parceito.getPessoa().getUsuario());
		    manager.persist(parceito.getPessoa());
			manager.merge(parceito);
			txt.commit();
		} catch (Exception e) {
			txt.rollback();
			e.printStackTrace();
		}
		
	}
	
}
