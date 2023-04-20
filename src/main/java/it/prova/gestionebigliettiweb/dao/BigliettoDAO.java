package it.prova.gestionebigliettiweb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionebigliettiweb.model.Biglietto;

public interface BigliettoDAO extends IBaseDAO<Biglietto>{

	void setEntityManager(EntityManager entityManager);

	List<Biglietto> findByExample(Biglietto input) throws Exception;

}
