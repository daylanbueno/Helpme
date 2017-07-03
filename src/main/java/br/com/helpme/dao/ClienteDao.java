package br.com.helpme.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import br.com.helpme.modelo.PessoaFisica;

public class ClienteDao {

	@Inject
	private EntityManager manager;


	public void salvarCliente(PessoaFisica cliente){
		EntityTransaction txt = manager.getTransaction();
		try {
			txt.begin();
				manager.persist(cliente.getPessoa().getEndereco());
				manager.persist(cliente.getPessoa().getContato());
				manager.persist(cliente.getPessoa().getUsuario());
			    manager.persist(cliente.getPessoa());
				manager.merge(cliente);
			txt.commit();
			} catch (Exception e) {
				txt.rollback();
				e.printStackTrace();
			}
			
	}
}
