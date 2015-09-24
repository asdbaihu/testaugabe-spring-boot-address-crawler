package de.regis24.persistence.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import de.regis24.persistence.dao.GenericDao;

/**
 * Created by vbourdine on 23.09.2015.
 * @param <T> Model type parameter
 * Class implements some basic crud operations
 */
@Component
public class GenericDaoImp<T> implements GenericDao<T> {
	
	private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImp.class);
	
	private JpaRepository<T, Long> repository;

	public List<T> saveAll(List<T> models) {
		LOG.debug("performing saveAll: "+models);
		try{
			List<T> result = repository.save(models);
			return result;
		}catch(HibernateException e){	
			LOG .error("Something went wrong: " + "can't save model " + models);
			e.printStackTrace();
		}
		return null;
	}

	public T get(Long id) {
		LOG.debug("performing get by id: "+id);
		try{
			T result = repository.getOne(id);
			return result;
		}catch(HibernateException e){	
			LOG .error("Something went wrong - can get model by id: " + id);
			e.printStackTrace();
		}
		return null;
	}

}
