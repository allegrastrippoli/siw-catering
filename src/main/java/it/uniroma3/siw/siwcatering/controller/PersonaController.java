package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.model.Persona;
import it.uniroma3.siw.siwcatering.service.PersonaService;
import it.uniroma3.siw.siwcatering.validator.PersonaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonaController {

	@Autowired 
	private PersonaService ps;
	
	@Autowired
	private PersonaValidator pv;
	
	/*GET per lettura
	 *POST per scrittura */
	
	
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute("persona") Persona persona, Model model, BindingResult bindingResult) {
		
		pv.validate(persona, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			ps.save(persona);
			model.addAttribute("persona", persona);
			return "persona.html";
		}
		return "personaForm.html";
	}
	
	@GetMapping("/persona")
	public String getPersone(Model model) {
		List<Persona> persone = ps.findAll();
		model.addAttribute("persone", persone);
		return "persone.html";
		
	}
	
	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model) {
		Persona persona = ps.findById(id);
		model.addAttribute("persona", persona);
		return "persona.html";
	}

	
	@GetMapping("/personaForm")
	public String getPersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "personaForm.html";
	}
	
}
