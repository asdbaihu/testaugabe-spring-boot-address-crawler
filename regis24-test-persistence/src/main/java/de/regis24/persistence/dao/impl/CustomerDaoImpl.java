package de.regis24.persistence.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import de.regis24.persistence.dao.CustomerDao;
import de.regis24.persistence.dao.GenericDao;
import de.regis24.persistence.model.Customer;

/**
 * Created by vbourdine on 23.09.2015.
 */
@Component
public class CustomerDaoImpl implements CustomerDao  {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
//	@Qualifier("CustomerRepository")
	@Autowired
	private JpaRepository<Customer, Long> repository;
	
	public List<Customer> saveAll(List<Customer> models) {
		LOG.debug("performing saveAll: "+models);
		try{
			List<Customer> result = repository.save(models);
			return result;
		}catch(HibernateException e){	
			LOG .error("Something went wrong: " + "can't save model " + models);
			e.printStackTrace();
		}
		return null;
	}

	public Customer get(Long id) {
		LOG.debug("performing get by id: "+id);
		try{
			Customer result = repository.getOne(id);
			return result;
		}catch(HibernateException e){	
			LOG .error("Something went wrong - can get model by id: " + id);
			e.printStackTrace();
		}
		return null;
	}

}
