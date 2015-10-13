package br.com.danese.multitenancy.dao;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.danese.multitenancy.model.SelecionadorDeEmpresa;

public class BaseDAO implements Serializable {

	private static final long serialVersionUID = -1981959903215446402L;

	@Inject
	protected Logger log;

	@Inject
	protected EntityManager em;
	
	protected Session session() {
		Session session = em.unwrap(Session.class);
		
		session.enableFilter("filtroEmpresa").setParameter("paramEmpresa", SelecionadorDeEmpresa.empresa());
		
		return session;
	}

}
