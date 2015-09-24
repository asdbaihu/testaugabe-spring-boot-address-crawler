package de.regis24.service.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.regis24.app.CustomerData;

public class Regis24Configurer implements UrlCrawlConfigurer{

	private static final Logger LOG = LoggerFactory.getLogger(Regis24Configurer.class);
	private String newAddress;
	
	public CustomerData crawlAddress(CustomerData customer){
		LOG.debug("in obtainAdress");
		Document doc;
		CustomerData result = customer;//(CustomerData)customer.clone();
		try {
			doc = Jsoup.connect(customer.getUrl()).get();
			Elements questions = doc.select("div#c158 div.contentText");
			
			LOG.debug("will call the Configurer: Regis24Configurer");
			
			for (Element link : questions) {
				newAddress = link.child(0).html().replace(" <br>", ", ");
				LOG.debug("Obtained customer address: "+newAddress);
			}
			
			try{
				if(result.getAddresses() != null 
						&& result.getAddresses().contains(newAddress)){
					return result;
				}else{
					if(result.getAddresses() == null) result.setAddresses(new ArrayList<String>());
					result.getAddresses().add(newAddress);
				}
			}catch(NullPointerException e){
				List<String> adresses = new ArrayList<String>();
				adresses.add(newAddress);
				result.setAddresses(new ArrayList<String>());
//				result.getAddresses().add(newAddress);
			}
			return result;
		} catch (IOException e) {
			LOG.debug(e.getMessage());
			return customer;
		}
	}

}
