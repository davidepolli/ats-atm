package it.noema.ats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.noema.ats.model.ATM;
import it.noema.ats.service.ATMService;

@RestController
@RequestMapping(path = "/api/atm", produces = {"application/json"})
public class APIController {

	@Autowired
	private ATMService atmService;
	
	@GetMapping(value = "all")
	private List<ATM> findAll() {
		return atmService.findAll();
	}
	
	
	@GetMapping(value = "city/{city}")
	private List<ATM> findByCity(@PathVariable String city) {
		return atmService.findByCity(city);
	}
	
//	@GetMapping(value = "search/{keyword}")
//	private List<ATM> findByKeyword(@PathVariable String keyword) {
//		return atmService.findByKeyword(keyword);
//	}
	
	@GetMapping(value = "filter")
	private List<ATM> filterByKeyword(@RequestParam String keyword) {
		return atmService.findByKeyword(keyword);
	}

}
