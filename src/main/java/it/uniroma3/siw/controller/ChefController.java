package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.BuffetService;
import it.uniroma3.siw.service.ChefService;
import it.uniroma3.siw.validator.ChefValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	@Autowired
	private BuffetService buffetService;


	/*GET per lettura
	 *POST per scrittura */


	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}

	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {

		chefValidator.validate(chef, bindingResult);

		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", this.chefService.findById(chef.getId()));
			return "chef.html";
		}
		return "chefForm.html";
	}

	@GetMapping("/admin/chefDelete/{id}")
	public String  confirmDeleteChef (@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefDelete.html";
	}


	@PostMapping("/admin/chefDelete/{id}")
	public String deleteChef(@PathVariable("id") Long id, Model model) {
		chefService.deleteById(id);
		return "chefSuccess.html";

	}

		@Transactional
	@PostMapping("/admin/chefEdited/{id}")
	public String editedChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {

		Chef oldChef = chefService.findById(id);

		if(!chef.getNome().equals(oldChef.getNome()))
			chefValidator.validate(chef, bindingResults);

		if(!bindingResults.hasErrors()) {

			oldChef.setId(chef.getId());
			oldChef.setNome(chef.getNome());
			oldChef.setCognome(chef.getCognome());
			oldChef.setNazionalita(chef.getNazionalita());
			oldChef.setBuffets(chef.getBuffets());
			this.chefService.save(oldChef);
			model.addAttribute("chef", oldChef);
			return "chef.html";
		}
			return "chefEdit.html";

	}


	@GetMapping("/admin/chefEdit/{id}")
	public String editChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chefEdit.html";
	}



	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}




	@GetMapping("/chefList")
	public String getChefList( Model model) {
		//List<Chef>chef= chefService.findAll();
		//model.addAttribute("chef", chef);

		//Long howmany= chefService.Count();
		//model.addAttribute("howmany", howmany);

		List<Chef> chef= chefService.findAllByOrderByCognomeAsc();
		model.addAttribute("chef", chef);
		return "chefList.html";
	}



}
