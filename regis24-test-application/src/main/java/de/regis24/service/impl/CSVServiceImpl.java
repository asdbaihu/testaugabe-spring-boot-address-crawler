package de.regis24.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.regis24.app.CustomerData;
import de.regis24.service.AddressAppException;
import de.regis24.service.CSVService;

/**
 * Created by vbourdine on 22.09.2015.
 */
@Component
public class CSVServiceImpl implements CSVService {
	
	private static Logger LOG = Logger.getLogger(CSVServiceImpl.class);
	
//	csv/customer_urls.csv
	@Value("${csv.file}")
	private String csvfile;
	
	@Override
	public List<CustomerData> process() throws AddressAppException {
		 
		LOG.debug("Processing CSV file: "+ this.csvfile);
		// TODO Auto-generated method stub
		// TODO dummy implementation - implement...
//		... read(String csvFile)
//			move processed CSVFile to another dir
		
		List<CustomerData> customerData = new ArrayList<CustomerData>();
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
		
		return customerData;
	}
	
	public List<CustomerData> read(String csvFile) throws AddressAppException {
//		// TODO implement using Super CSV or other CSV API
		return null;
	}


    public void moveCSVFile(String csvFile) throws AddressAppException {
    	//TODO: implement move processed csv file to other dir
    }

	/*
	 * Processes calle csv file
	 * @return list of CustomerData filled by csv
	 */
//	public List<CustomerData> processCSVFile() throws AddressAppException {
//		// TODO implement
//		... read(String csvFile)
//			moveCSVFile
//		return null;
//	}

}

