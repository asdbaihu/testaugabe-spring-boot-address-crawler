package de.regis24.service;

import java.util.List;

import de.regis24.app.CustomerData;

/**
 * Created by vbourdine on 22.09.2015.
 */

public interface CSVService {
	

    /**
	 * @return customer data from processed csv file
	 * @throws AddressAppException
	 */
	public List<CustomerData> process() throws AddressAppException;

}
