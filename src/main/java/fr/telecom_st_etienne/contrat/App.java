package fr.telecom_st_etienne.contrat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration
@ComponentScan(basePackages = "fr.telecom_st_etienne.contrat")
@EnableJpaRepositories(basePackages = "fr.telecom_st_etienne.contrat.dao", entityManagerFactoryRef = "entityManagerFactory")
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class App implements WebMvcConfigurer{
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/style/**").addResourceLocations("/style/");
	}
	 
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}