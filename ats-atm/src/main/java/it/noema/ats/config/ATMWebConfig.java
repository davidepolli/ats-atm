package it.noema.ats.config;

import java.net.MalformedURLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import it.noema.ats.config.util.ATM2RepositoryPopulatorFactoryBean;

@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = "it.noema.ats")
public class ATMWebConfig {

	@Value("${atm.endpoint.url}")
	private String atmsURL;
	
	@Bean
    public ATM2RepositoryPopulatorFactoryBean repositoryPopulator() {
		ATM2RepositoryPopulatorFactoryBean factory = new ATM2RepositoryPopulatorFactoryBean();
		try {
			factory.setResources(new Resource[] {new UrlResource(atmsURL)});
		} catch (MalformedURLException e) {
			
			if (_log.isWarnEnabled()) {
				_log.warn("Error: try to load ATMs from classpath", e);
			}
			
			factory.setResources(new Resource[] { new ClassPathResource("ATMs.json")});
		}
        return factory;
    }

    @Bean
    EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("atm-web");
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
    
    
	private static final Logger _log = LoggerFactory
			.getLogger(ATMWebConfig.class);
}
