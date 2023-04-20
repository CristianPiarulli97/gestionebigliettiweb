package it.prova.gestionebigliettiweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionebigliettiweb.dao.BigliettoDAO;
import it.prova.gestionebigliettiweb.listener.LocalEntityManagerFactoryListener;
import it.prova.gestionebigliettiweb.model.Biglietto;

public class BigliettoServiceImpl implements BigliettoService {

	private BigliettoDAO bigliettoDao;
	
	@Override
	public void setBigliettoDao(BigliettoDAO bigliettoDao) {
		this.bigliettoDao = bigliettoDao;
		
		
	}

	@Override
	public List<Biglietto> listAll() throws Exception {
		// questo è come una connection
				EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

				try {
					// uso l'injection per il dao
					bigliettoDao.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return bigliettoDao.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
				}
	}

	@Override
	public Biglietto caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
				EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

				try {
					// uso l'injection per il dao
					bigliettoDao.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return bigliettoDao.findOne(idInput);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
				}
	}

	@Override
	public void aggiorna(Biglietto input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		try {

			// è un cambiamento del database,
			// quindi devo iniziare una Transaction
			entityManager.getTransaction().begin();

			// injection
			bigliettoDao.setEntityManager(entityManager);

			// esecuzione metodo
			bigliettoDao.update(input);

			// faccio il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// faccio rollback se non va a buon fine
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void inserisciNuovo(Biglietto input) throws Exception {
		// questo è come una connection
				EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					bigliettoDao.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					bigliettoDao.insert(input);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
				}
	}

	@Override
	public void rimuovi(Long idBigliettoToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();

			// injection
			bigliettoDao.setEntityManager(entityManager);

			// esecuzione metodo
			bigliettoDao.delete(bigliettoDao.findOne(idBigliettoToRemove));

			// faccio il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// faccio rollback se non va a buon fine
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public List<Biglietto> findByExample(Biglietto input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Articolo a where a.id = a.id ");

		if (StringUtils.isNotEmpty(input.getProvenzienza())) {
			whereClauses.add(" b.provenienza  like :provenienza ");
			paramaterMap.put("provenienza", "%" + input.getProvenzienza() + "%");
		}
		if (StringUtils.isNotEmpty(input.getDestinazione())) {
			whereClauses.add(" b.destinazione  like :destinazione ");
			paramaterMap.put("destinazione", "%" + input.getDestinazione() + "%");
		}
		if (input.getPrezzo() != null) {
			whereClauses.add("b.data >= :data ");
			paramaterMap.put("data", input.getData());
		}
		if (input.getPrezzo() != null) {
			whereClauses.add("b.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", input.getPrezzo());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Biglietto> typedQuery = entityManager.createQuery(queryBuilder.toString(), Biglietto.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}
		
		return typedQuery.getResultList();
	}
	

}
