package it.noema.ats.config.util;

import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.ResourceReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ATM2RepositoryPopulatorFactoryBean extends Jackson2RepositoryPopulatorFactoryBean {

	private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
	 

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean#getResourceReader()
	 */
	@Override
	protected ResourceReader getResourceReader() {
		return new ATM2ResourceReader(DEFAULT_MAPPER);
	}

}
