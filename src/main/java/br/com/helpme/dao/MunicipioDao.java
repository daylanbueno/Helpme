package br.com.helpme.dao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.event.ExceptionQueuedEvent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.QueryException;

import br.com.helpme.modelo.Estado;
import br.com.helpme.modelo.Municipio;

public class MunicipioDao {

	@Inject
	private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public  List<Municipio> findMunicipiosByEstado(Estado estado){
		List<Municipio> lista =null;
		try {
			String jpql = "select m from Municipio m  where m.estado =:pEstado";
			Query query = manager.createQuery(jpql);
			query.setParameter("pEstado", estado);
			lista = query.getResultList();
			
		} catch (QueryException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
