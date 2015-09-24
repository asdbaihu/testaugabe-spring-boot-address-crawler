package de.regis24.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AutoPopulatingList;

import de.regis24.persistence.dao.CustomerDao;
import de.regis24.persistence.model.Address;
import de.regis24.persistence.model.Customer;

/**
 * Created by vbourdine on 23.09.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/persistence-test-context.xml"})
public class CustomerDaoImplTest{
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImplTest.class);

	@Autowired
	private CustomerDao dao;
	private static List<Customer> customers; 
	
	@Before
	public void createCustomer(){
		
//		public Customer(Long id, List<Address> addresses, String name) {
//			super();
//			this.id = id;
//			this.addresses = addresses;
//			this.name = name;
//		}
		
		customers = new ArrayList<Customer>();
		Customer customer = new Customer("TestCustomer1");
		customer.setAddresses( new AutoPopulatingList<Address>(Address.class));
		
//		customer.getAddresses().add(new Address("Anystreet 34, 10342, Munic, Germany"));
//		customer.getAddresses().add(new Address("Teststreet 9, 10342, Berlin, Germany"));
		customer.getAddresses().add(new Address("Anystreet 34, 10342, Munic, Germany", customer));
		customer.getAddresses().add(new Address("Teststreet 9, 10342, Berlin, Germany", customer));
		
		customers.add(customer);

		customer = new Customer("Regis24"); 
		customer.getAddresses().add(new Address("Regis24 GmbH, Zehdenicker Str. 21, 10119 Berlin", customer));

		customers.add(customer) ;
	}

	@Test
	@Transactional
	public void testSaveAll() {

		List<Customer> result = dao.saveAll(customers);

		Assert.assertNotNull(result.get(0));
		Assert.assertTrue(result.get(0).getId() > -1);

		Iterator<Customer> iter = result.iterator();
		int i = 0;

		while (iter.hasNext()) {
			Customer customer = iter.next();
			LOG.debug("Customer data: " + customer);
			Assert.assertEquals(result.get(i), customer);
			i++;
		}
		
		customers = result;
	}
	
	@Test
	@Transactional
	public void testGet() {
		
		// save them to get customers with Ids
		List<Customer> result = dao.saveAll(customers);
		
//		check if dao can get somethinf by id
		for(Customer tmp: result){
			Customer savedCustomer = dao.get(tmp.getId());
			
			Assert.assertNotNull(dao.get(tmp.getId()));
			LOG.debug("Customer data retreived by dao.get(Id): " + savedCustomer);
			
		}
	}
	
}
