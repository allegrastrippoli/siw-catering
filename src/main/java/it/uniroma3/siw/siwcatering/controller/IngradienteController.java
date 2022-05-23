package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.model.Ingradiente;
import it.uniroma3.siw.siwcatering.service.IngradienteService;
import it.uniroma3.siw.siwcatering.validator.IngradienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IngradienteController {
	
	@Autowired 
	private IngradienteService is;
	
	@Autowired
	private IngradienteValidator iv;
	

	@GetMapping("/ingradienteForm")
	public String addIngradiente(Model model) {
		model.addAttribute("ingradiente", new Ingradiente());
		return "ingradienteForm.html";
	}
	

	@PostMapping("/ingradiente")
	public String addIngradiente(@Valid @ModelAttribute("ingradiente") Ingradiente ingradiente, Model model, BindingResult bindingResults) {
		
		iv.validate(ingradiente, bindingResults);
		
		if(!bindingResults.hasErrors()) {
			is.save(ingradiente);
			model.addAttribute("ingradiente", ingradiente);
			return "ingradiente.html";
		}
		return "ingradienteForm.html";
		
	}
	
	
	@GetMapping("/ingradienteDelete/{id}")
	public String deleteIngradiente(@PathVariable("id") Long id, Model model) {

		is.deleteById(id);

		return "index.html";

	}


	@GetMapping("/ingradienteModify/{id}")
	public String modifyIngradiente(@PathVariable("id") Long id, Model model) {
		Ingradiente ingradiente = is.findById(id);
		model.addAttribute("ingradiente", ingradiente);
		return "ingradienteForm.html";
	}
	
	
	
}
