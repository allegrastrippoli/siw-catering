package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.validator.BuffetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BuffetController {
	
	@Autowired 
	private BuffetService bs;
	
	@Autowired
	private BuffetValidator bv;
	
	

}
