package de.regis24.service;

import java.util.List;

import de.regis24.app.CustomerData;

/**
 * Created by vbourdine on 22.09.2015.
 */

public interface CrawlService {
	
	List<CustomerData> crawl(List<CustomerData> data);

}
