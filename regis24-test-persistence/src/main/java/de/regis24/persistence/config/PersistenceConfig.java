package de.regis24.persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Persistence configuration class defines datasource configuration
 * ,repository packages and transaction properties
 */

@Configuration
@EnableJpaRepositories(basePackages = {"de.regis24.persistence.repository"})
@EnableTransactionManagement
public class PersistenceConfig {

	private static Logger LOG = LoggerFactory.getLogger(PersistenceConfig.class);

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Value("${hibernate.default_schema}")
	private String hibernateSchema;

	@Bean()
	public DataSource getDataSource() {
		LOG.debug("Getting datasource.");
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();

		lcemfb.setDataSource(getDataSource());
		lcemfb.setPackagesToScan("de.regis24.persistence.model");

		lcemfb.setPersistenceUnitName("regis24");

		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);

		Properties ps = new Properties();
		ps.put("hibernate.default_schema", hibernateSchema);
		ps.put("hibernate.dialect", hibernateDialect);
		ps.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		ps.put("hibernate.show_sql", hibernateShowSql);
		ps.put("flushMode", "COMMIT/AUTO");
		lcemfb.setJpaProperties(ps);

		lcemfb.afterPropertiesSet();

		return lcemfb;

	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return tm;

	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
