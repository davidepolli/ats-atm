package it.noema.ats.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.noema.ats.service.ATMService;

@Controller
@RequestMapping("/")
/**
 * base controller for the homepage
 * @author Noema STI
 *
 */
public class MainController {
	
	@Value("${rest.service.find.by.city}")
	private String findByCityServiceURL;
	@Value("${rest.service.keyword.filter}")
	private String keywordFilterServiceURL;

	@Autowired
	private ATMService atmService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String defaultPage(Model model) {
		
		// retrieve atm cities
		List<String> cities = atmService.getAllCities();
		
		if (_log.isDebugEnabled()) {
			_log.debug("[defaultPage] getting the home page, ["+(cities != null ? cities.size() : "no cities ")+"] retrieved");
		}
		// set the cities in the model
		model.addAttribute("cities", cities);
		// passing the service URLs
		model.addAttribute("findByCityServiceURL", findByCityServiceURL);
		model.addAttribute("keywordFilterServiceURL", keywordFilterServiceURL);
		
		return "home";
	}
	
	private static final Logger _log = LoggerFactory.getLogger(MainController.class);
}
