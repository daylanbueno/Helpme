package br.com.helpme.teste;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.helpme.modelo.Estado;

public class TesteEstado {
	
	@Inject
	private   EntityManager manager;

	public static void main(String[] args) {
		TesteEstado  test = new TesteEstado();
		test.salvar();
	}
	
	public void salvar(){
		EntityManagerFactory facricar = Persistence.createEntityManagerFactory("helpme-pu");
		manager = facricar.createEntityManager();
//		
//		EntityTransaction txt = manager.getTransaction();
////		
////		Estado estado = new Estado();
////		estado.setNome("Distrito Federal");
////		estado.setSigla("DF");
////		txt.begin();
////		manager.merge(estado);
//		txt.commit();
		
	}

}
