package de.regis24.app;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.regis24.service.CrawlService;
import de.regis24.service.config.UrlCrawlConfigurer;

/**
 * Created by vbourdine on 22.09.2015.
 */

public class CrawlerTask implements Callable<CustomerData> {

	private static final Logger LOG = LoggerFactory.getLogger(CrawlService.class);
	protected static final String DEFAULT_CONFIGURER = "DefaultConfigurer";
	private static final String CONFIG_PACKAGE = "de.regis24.service.config.";
	private CustomerData customer;

	public CrawlerTask(CustomerData customerData) {
		this.customer = customerData;
	}

	public CustomerData call() throws Exception {
		Class<?> clazz = Class.forName(CONFIG_PACKAGE+DEFAULT_CONFIGURER);
		if(!customer.getConfigurer().isEmpty()) {
			try{
				clazz = Class.forName(CONFIG_PACKAGE+customer.getConfigurer());
			}catch(ClassNotFoundException e){
				LOG.debug("No configurer for name: "+customer.getConfigurer()+ " using default one");
			}
		}
		UrlCrawlConfigurer configurer = (UrlCrawlConfigurer)clazz.newInstance();
		LOG.debug("Created configurer: "+configurer);
		return configurer.crawlAddress(customer);
	}

}
