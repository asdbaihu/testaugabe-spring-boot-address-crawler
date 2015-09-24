package de.regis24.service;

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

import de.regis24.app.CustomerData;
import de.regis24.config.TestApplicationConfig;

/**
 * Created by vbourdine on 22.09.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
public class CrawlServiceImplTest{
	
	private static final Logger LOG = LoggerFactory.getLogger(CrawlServiceImplTest.class);
	private static List<CustomerData> customerData;
	
	@Autowired
	private CrawlService crawlService; 
	
	@Before
	public void createCustomerData(){
		customerData = new ArrayList<CustomerData>();
		// test ClassNotFoundException
		CustomerData customer = new CustomerData("FooBar", 
				"http://www.foo_bar.de", 
				"Foo Bar",
				1L,
				new ArrayList<String>());
		customerData.add(customer) ;
		
		//test empty Configurer name
		customer = new CustomerData("", 
				"http://www.foo_wiz.de", 
				"Foo Wiz",
				2L,
				new ArrayList<String>());
		customerData.add(customer) ;
		// test regular data
		 customer = new CustomerData("Regis24Configurer", 
				"http://www.regis24.de/impressum.php", 
				"regis24",
				3L,
				new ArrayList<String>());
		customerData.add(customer) ;
	}

	@Test
	public void testCrawl() {
		
		 List<CustomerData> result = crawlService.crawl(customerData);
		 Assert.assertNotNull(result.get(0));
		 Assert.assertNotNull(result.get(0).getAddresses().get(0));
	}
}
