package de.regis24.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;

import de.regis24.app.config.ApplicationConfig;

/**
 * Created by vbourdine on 22.09.2015.
 */

@SpringBootApplication
@EnableScheduling
@ContextConfiguration(classes = ApplicationConfig.class)
public class Application {
	
	public static void main (String[] args) throws Exception{
		SpringApplication.run(Application.class);
	}

}
