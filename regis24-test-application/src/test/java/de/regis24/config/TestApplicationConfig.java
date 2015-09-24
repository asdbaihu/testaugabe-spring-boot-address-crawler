package de.regis24.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by vbourdine on 24.09.2015.
 */


@Configuration
@ComponentScan(basePackages = {
//	app
		"de.regis24",
        "de.regis24.service",
//        "de.regis24.app.scheduled",
        "de.regis24.service.impl",
//   persistence
        "de.regis24.persistence.model",
        "de.regis24.persistence.dao",
        "de.regis24.persistence.dao.impl",
        "de.regis24.persistence.repository",
//		,"de.regis24.jsoup"
})
@EnableScheduling
public class TestApplicationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("conf/regis24.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}