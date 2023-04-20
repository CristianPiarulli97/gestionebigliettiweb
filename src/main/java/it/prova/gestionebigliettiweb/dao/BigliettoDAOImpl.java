package it.prova.gestionebigliettiweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionebigliettiweb.listener.LocalEntityManagerFactoryListener;
import it.prova.gestionebigliettiweb.model.Biglietto;

public class BigliettoDAOImpl implements BigliettoDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	@Override
	public List<Biglietto> list() throws Exception {
		return entityManager.createQuery("from Biglietto", Biglietto.class).getResultList();
	}

	@Override
	public Biglietto findOne(Long id) throws Exception {
		return entityManager.find(Biglietto.class, id);
	}

	@Override
	public void update(Biglietto input) throws Exception {
		if (input == null)
			throw new Exception("Problema valore input");
		input = entityManager.merge(input);
		
	}

	@Override
	public void insert(Biglietto input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Biglietto input) throws Exception {
		if (input == null)
			throw new Exception("Problema valore input");
		entityManager.remove(entityManager.merge(input));
		
	}
	
	public List<Biglietto> findByExample(Biglietto input) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Biglietto a where a.id = a.id ");

		if (StringUtils.isNotEmpty(input.getProvenzienza())) {
			whereClauses.add(" b.provenienza  like :provenienza ");
			paramaterMap.put("provenienza", "%" + input.getProvenzienza() + "%");
		}
		if (StringUtils.isNotEmpty(input.getDestinazione())) {
			whereClauses.add(" b.destinazione  like :destinazione ");
			paramaterMap.put("destinazione", "%" + input.getDestinazione() + "%");
		}
		if (input.getData() != null) {
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
