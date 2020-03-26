package it.noema.ats.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.noema.ats.model.ATM;
import it.noema.ats.repository.ATMRepository;
import it.noema.ats.service.ATMService;

@Service
public class ATMServiceImpl implements ATMService {

	@Autowired
	private ATMRepository atmRepository;
	
	public List<ATM> findAll(){
		return atmRepository.findAll();
	}

	@Override
	public List<ATM> findByCity(String city) {
		return atmRepository.findByCityContainingIgnoreCase(StringUtils.lowerCase(city));
	}

	@Override
	public List<ATM> findByKeyword(String keyword) {
		return atmRepository.findByKeyword(StringUtils.lowerCase(keyword));
	}
	
	@Override
	public List<String> getAllCities() {
		return atmRepository.findCities();
	}
}
