package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.ChefService;
import it.uniroma3.siw.siwcatering.validator.ChefValidator;
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
public class ChefController {

	@Autowired 
	private ChefService ps;

	@Autowired
	private BuffetService bs;

	@Autowired
	private ChefValidator pv;

	
	/*GET per lettura
	 *POST per scrittura */
	
	
	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, Model model, BindingResult bindingResult) {
		
		pv.validate(chef, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			ps.save(chef);
			model.addAttribute("chef", chef);
			return "chef.html";
		}
		return "chefForm.html";
	}
	
	@GetMapping("/chefForm")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}

	@GetMapping("/chefDelete/{id}")
	public String deleteChef(@PathVariable("id") Long id, Model model) {

		ps.deleteById(id);

		return "index.html";

	}


	@GetMapping("/chefModify/{id}")
	public String modifyChef(@PathVariable("id") Long id, Model model) {
		Chef chef = ps.findById(id);
		model.addAttribute("chef", chef);
		return "chefForm.html";
	}


	@GetMapping("/chefList")
	public String getChefList( Model model) {
		List<Chef>chef= ps.findAll();
		model.addAttribute("chef", chef);
		return "chefList.html";
	}

	
	/*
	@GetMapping("/chefVisualizeBuffet")
	public String getBuffet( Model model) {
		List<Buffet>buffet= bs.findAll();
		model.addAttribute("buffet", buffet);
		return "BuffetList.html";
	}
	 */

}
