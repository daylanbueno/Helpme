package br.com.helpme.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.helpme.modelo.Estado;
import br.com.helpme.util.HibernateUtil;

public class EstadoDao {
	
	@SuppressWarnings("unchecked")
	public List<Estado> carregarEstados(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta= sessao.createCriteria(Estado.class);
			List<Estado> resultado= consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}

}
