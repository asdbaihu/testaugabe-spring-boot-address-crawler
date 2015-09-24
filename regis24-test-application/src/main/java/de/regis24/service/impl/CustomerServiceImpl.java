package de.regis24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.regis24.persistence.dao.GenericDao;
import de.regis24.persistence.model.Customer;
import de.regis24.service.CustomerService;

/**
 * Created by vbourdine on 22.09.2015.
 */
@Component
public class CustomerServiceImpl extends GenericPersistenceServiceImpl<Customer>
		implements CustomerService{
	
//	Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
//	@Qualifier("CustomerDao")
	@Autowired
	GenericDao<Customer> dao;

//	@Transactional
//	public List<Customer> saveAll(List<Customer> customers) {
//		
//		try{
//			List<Customer> result = repository.save(customers);
//			return result;
//		}catch(HibernateException e){	
//			LOG .error("Something went wrong: " + "can't save model " + customers);
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Transactional
//	public Customer get(Long id) {
//		try{
//			return repository.findOne(id);
//		}catch(HibernateException e){	
//			LOG .error("Something went wrong: " + "can't find customer with id" + id);
//			e.printStackTrace();
//		}
//		return null;
//	}

}
