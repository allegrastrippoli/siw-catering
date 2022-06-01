package it.uniroma3.siw.controller;
import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.BuffetService;
import it.uniroma3.siw.service.ChefService;
import it.uniroma3.siw.service.PiattoService;
import it.uniroma3.siw.validator.BuffetValidator;
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
	private BuffetService buffetService;

	@Autowired
	private BuffetValidator buffetValidator;

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private ChefService chefservice;


	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, Model model, BindingResult bindingResult) {

		buffetValidator.validate(buffet, bindingResult);

		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet.html";
		}
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		model.addAttribute("chefs", chefservice.findAll());
		return "buffetForm.html";
	}

	@GetMapping("/admin/buffetForm")
	public String getBuffetForm(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		model.addAttribute("chefs", chefservice.findAll());
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}

	@GetMapping("/buffetDelete/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.deleteById(id);
		return "index.html";

	}


	@GetMapping("/buffetModify/{id}")
	public String modifyBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffetForm.html";
	}

	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "buffet.html";
	}

	@GetMapping("/buffetList")
	public String getBuffetList( Model model) {
		List<Buffet> buffet= buffetService.findAll();
		model.addAttribute("buffet", buffet);
		return "buffetList.html";
	}


}
