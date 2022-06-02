package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Chef;
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


	/*GET per lettura
	 *POST per scrittura */


	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, Model model, BindingResult bindingResult) {

		chefValidator.validate(chef, bindingResult);

		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", this.chefService.findById(chef.getId()));
			return "chef.html";
		}
		return "chefForm.html";
	}

	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}

	@GetMapping("/admin/chefDelete/{id}")
	public String deleteChef(@PathVariable("id") Long id, Model model) {
		chefService.deleteById(id);
		return "index.html";

	}

		@Transactional
	@PostMapping("/admin/chefEdited/{id}")
	public String editChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {

		if(!bindingResults.hasErrors()) {
			Chef oldChef = chefService.findById(id);
			oldChef.setId(chef.getId());
			oldChef.setNome(chef.getNome());
			oldChef.setNome(chef.getCognome());
			oldChef.setNazionalita(chef.getNazionalita());

			this.chefService.save(oldChef);
			model.addAttribute("chef", chef);
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
		model.addAttribute("chef", chefService.findById(id));
		return "chef.html";
	}


	@GetMapping("/chefList")
	public String getChefList( Model model) {
		List<Chef>chef= chefService.findAll();
		model.addAttribute("chef", chef);
		return "chefList.html";
	}



}
