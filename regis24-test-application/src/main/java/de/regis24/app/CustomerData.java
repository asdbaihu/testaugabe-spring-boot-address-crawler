package de.regis24.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vbourdine on 22.09.2015.
 */

public class CustomerData {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerData.class);
	private String configurer;
	private String url;
	private String name;
	private Long id;
	private List<String> addresses;
	
	public CustomerData(String configurer, String url, String name, Long id,
			List<String> adresses) {
		super();
		this.configurer = configurer;
		this.url = url;
		this.name = name;
		this.id = id;
		this.addresses = adresses;
	}

	public Object clone(){
		try{
 			return super.clone();
		}catch(Exception e){
			LOG.error("Error cloning CustomerData:"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public String getConfigurer() {
		return configurer;
	}
	public void setConfigurer(String configurer) {
		this.configurer = configurer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CustomerData [LOG=" + LOG + ", configurer=" + configurer
				+ ", url=" + url + ", name=" + name + ", id=" + id
				+ ", adresses=" + addresses + "]";
	}
	
}
