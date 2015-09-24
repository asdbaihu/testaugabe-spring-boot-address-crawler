package de.regis24.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.regis24.persistence.dao.GenericDao;
import de.regis24.service.GenericPersistenceService;

/**
 * Created by vbourdine on 23.09.2015.
 * @param <T> Model type parameter
 * implements some basic crud operations
 */
public class GenericPersistenceServiceImpl<T> implements
		GenericPersistenceService<T> {
	
	private static final Logger LOG = LoggerFactory.getLogger(GenericPersistenceServiceImpl.class);
	
	private GenericDao<T> dao;	

	@Override
//	@Transactional
	public List<T> saveAll(List<T> models) {
		LOG.debug("Performing saveAll: "+models);
		return dao.saveAll(models);
	}

	@Override
//	@Transactional
	public T get(Long id) {
		LOG.debug("Performing get by id: "+id);
		return dao.get(id);
	}

}
