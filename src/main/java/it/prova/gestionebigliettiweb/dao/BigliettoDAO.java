package it.prova.gestionebigliettiweb.dao;

import javax.persistence.EntityManager;

import it.prova.gestionebigliettiweb.model.Biglietto;

public interface BigliettoDAO extends IBaseDAO<Biglietto>{

	void setEntityManager(EntityManager entityManager);

}
