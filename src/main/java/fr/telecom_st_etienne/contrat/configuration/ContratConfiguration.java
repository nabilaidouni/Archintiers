package fr.telecom_st_etienne.contrat.configuration;

import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class ContratConfiguration {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(82);
		return factory;
	}
/*
	@Bean(name = "dataSource")
	@Scope("singleton")
	public DataSource getDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUsername("root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/kanban");
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setValidationInterval(34000);
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setPersistenceUnitName("entityManagerFactory");
		entityManagerFactoryBean.setPackagesToScan("fr.telecom_st_etienne.fx.kanban.business");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter );
		return entityManagerFactoryBean;
	}
	*/
	
	@Bean(name = "dataSource")
	@Scope("singleton")
	public DataSource getDataSource() {
	DataSource dataSource = new DataSource();
	dataSource.setUsername("sa");
	dataSource.setPassword("");
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl("jdbc:h2:mem:contrat");
	dataSource.setValidationQuery("SELECT 1");
	dataSource.setValidationInterval(34000);
	dataSource.setTestOnBorrow(true);
	return dataSource;
	}

	    @Bean("entityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(getDataSource());
	        entityManagerFactoryBean.setPersistenceUnitName("entityManagerFactory");
	        entityManagerFactoryBean.setPackagesToScan("fr.telecom_st_etienne.contrat.business");
	        Properties jpaProperties = new Properties();
	        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
	        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        entityManagerFactoryBean.setJpaProperties(jpaProperties);
	        
	        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter );
	        return entityManagerFactoryBean;
	    }
	
	@Bean("viewResolver")
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("");
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		return resolver;
	}
	
	@Bean("transactionManager")
	public JpaTransactionManager getTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	
	
}