package de.shelp.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

@Startup
@Singleton
public class DataBuilder {

//	private static final Logger LOGGER = Logger.getLogger(DataBuilder.class);

	@PersistenceContext
	EntityManager em;

//	@Resource
//	private String username1, password1, username2, password2;

	@PostConstruct
	private void init() {

	}

}