package br.com.helpme.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ManagerProducer {

	private EntityManagerFactory fabrica;
	
	public ManagerProducer(){
		fabrica= Persistence.createEntityManagerFactory("helpme-pu");	
	}
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager(){
		return this.fabrica.createEntityManager();
	}
	
	
	public void cleseEntityManager(@Disposes EntityManager manager){
		manager.close();
	}
}
