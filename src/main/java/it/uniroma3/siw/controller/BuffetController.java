package it.uniroma3.siw.controller;
import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Chef;
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

import javax.transaction.Transactional;
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
	private ChefService chefService;


	@GetMapping("/admin/buffetForm")
	public String getBuffetForm(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		model.addAttribute("chefs", chefService.findAll());
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}

	@PostMapping("/admin/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {

		buffetValidator.validate(buffet, bindingResult);

		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "buffet.html";
		}
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		model.addAttribute("chefs", chefService.findAll());
		return "buffetForm.html";
	}



	@GetMapping("/admin/buffetDelete/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {

		Buffet buffet = buffetService.findById(id);
		List<Chef> chef = chefService.findAll();
		for(Chef c : chef) {
			if(c.getBuffets().contains(buffet)) {
				c.getBuffets().remove(buffet);
				chefService.save(c);
			}
		}

		buffetService.deleteById(id);
		return "adminindex.html";

	}

	@Transactional
	@PostMapping("/admin/buffetEdited/{id}")
	public String editedBuffef(@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {

		Buffet oldBuffet = buffetService.findById(id);

		if(!buffet.getNome().equals(oldBuffet.getNome()))
			buffetValidator.validate(buffet, bindingResults);

		if(!bindingResults.hasErrors()) {

			oldBuffet.setId(buffet.getId());
			oldBuffet.setNome(buffet.getNome());
			oldBuffet.setDescrizione(buffet.getDescrizione());
			oldBuffet.setChef(buffet.getChef());
			oldBuffet.setPiatti(buffet.getPiatti());

			this.buffetService.save(oldBuffet);
			model.addAttribute("buffet", oldBuffet);
			return "buffet.html";
		}
		return "buffetEdit.html";

	}


	@GetMapping("/admin/buffetEdit/{id}")
	public String editBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("chefList", chefService.findAll());
		model.addAttribute("piattoList", piattoService.findAll());
		return "buffetEdit.html";
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
