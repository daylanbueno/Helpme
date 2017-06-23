package br.com.helpme.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// carregando propriedades do cfx.hibernate
			Configuration configuracao = new Configuration().configure();
			
			 //registrando o serviço
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();
			//pegando a conf.. passando registro.=> construindo fabrica
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			// retornando a fabrica
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
