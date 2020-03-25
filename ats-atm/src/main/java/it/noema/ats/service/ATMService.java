package it.noema.ats.service;

import java.util.List;

import it.noema.ats.model.ATM;

public interface ATMService {

	public List<ATM> findAll();
	
	public List<ATM> findByCity(String city);
	
	public List<ATM> findByKeyword(String keyword);
}
