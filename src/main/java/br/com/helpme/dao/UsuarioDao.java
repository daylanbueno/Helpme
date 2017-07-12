package br.com.helpme.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.helpme.modelo.Usuario;

public class UsuarioDao {

	@Inject
	private EntityManager manager;

	
	public Usuario findUsuarioByUsuario(Usuario user){
		StringBuilder jpql = new  StringBuilder();
		jpql.append("SELECT usuario FROM Usuario usuario where usuario.email =:paramEmail");
		jpql.append(" AND usuario.senha =:paramSenha");
		
		Query query  = manager.createQuery(jpql.toString());
		query.setParameter("paramEmail", user.getEmail());
		query.setParameter("paramSenha", user.getSenha());
	
		List<Usuario> usuarios =  query.getResultList();
		if(usuarios !=null &&  !usuarios.isEmpty()){
			return usuarios.get(0);
		}else{
			return null;
		}
		
	}

}
