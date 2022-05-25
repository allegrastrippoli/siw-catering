package it.uniroma3.siw.siwcatering.controller;
import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.PiattoService;
import it.uniroma3.siw.siwcatering.validator.BuffetValidator;
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
public class BuffetController {
	
	@Autowired 
	private BuffetService bs;

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetValidator bv;

	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, Model model, BindingResult bindingResult) {

		bv.validate(buffet, bindingResult);

		if(!bindingResult.hasErrors()) {
			bs.save(buffet);
			model.addAttribute("buffet", buffet);
			model.addAttribute("chef", buffet.getChef());
			return "buffet.html";
		}
		return "buffetForm.html";
	}

	@GetMapping("/buffetForm")
	public String getBuffet(Model model) {
		/*
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		*/
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}

	@GetMapping("/buffetDelete/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {

		bs.deleteById(id);

		return "index.html";

	}


	@GetMapping("/buffetModify/{id}")
	public String modifyBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffetForm.html";
	}


	@GetMapping("/buffetList")
	public String getBuffetList( Model model) {
		List<Buffet> buffet= bs.findAll();
		model.addAttribute("buffet", buffet);
		return "buffetList.html";
	}


}
