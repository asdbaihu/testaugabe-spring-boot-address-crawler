package de.regis24.app.scheduled;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.regis24.app.CustomerData;
import de.regis24.app.util.ModelConvertUtil;
import de.regis24.persistence.model.Customer;
import de.regis24.service.AddressAppException;
import de.regis24.service.CSVService;
import de.regis24.service.CrawlService;
import de.regis24.service.CustomerService;
import de.regis24.service.MailService;

/**
 * Created by vbourdine on 22.09.2015.
 * Implements scheduled task
 */
/**
 * Class, performing Service calls and passing data between them
 *
 */
@Component
public class ScheduledTask{
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CSVService csvService;
	@Autowired
	private MailService mailService;
	@Autowired
	private CrawlService crawlService;
	
	/**
	 * calls main task on scheduled base, requesting methods of Services focused 
	 * on concerns like: obtaining csv-data, persistence, mailing 
	 */
//	@Scheduled(cron = "${cvs.read.schedule}")
	@Scheduled(fixedRate = 5000)
	public void performTask(){
		
//		TODO: 
//		- Call csv reader to get URLs and Crawler Configurations
//		- On result compare new address with the existing once and 
//		call Mail Service + Persistence Service if needed
		
		List<CustomerData> customerData = null;
		try {
			LOG.debug("performTask");
			
			customerData = csvService.process();
//			TODO: while not implemented use for test reasons dummy data
//			customerData = getCustomerData(customerData);
			customerData = crawlService.crawl(customerData);
			save(customerData);
		} catch (AddressAppException e) {
			LOG.error("Something went wrong: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtains customers from db by id
	 * @param customers
	 * @return
	 */
	private List<CustomerData> getCustomerData(List<CustomerData> customers) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Saves customers to db.
	 * @param customers customers URLs
	 * @return persisted customers
	 */
	public List<Customer> save(List<CustomerData> customerData) throws AddressAppException {
		
		List<Customer> customers = ModelConvertUtil.convertToCustomers(customerData);
		
		try{
			LOG.debug("Start persisting customers to db: " + customers);
			return customerService.saveAll(customers);
		} catch(Exception e){
			throw new AddressAppException("Something went wrong: "+"can't persist models: " + customers);
		}
	}	
}

