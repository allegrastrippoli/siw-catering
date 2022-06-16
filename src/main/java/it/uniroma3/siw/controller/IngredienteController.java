package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.IngredienteService;
import it.uniroma3.siw.service.PiattoService;
import it.uniroma3.siw.validator.IngredienteValidator;
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
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteValidator ingredienteValidator;

	@Autowired
	private PiattoService piattoService;

	@GetMapping("/admin/ingredienteForm")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}


	@PostMapping("/admin/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {

		ingredienteValidator.validate(ingrediente, bindingResults);

		if(!bindingResults.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "ingrediente.html";
		}
		return "ingredienteForm.html";

	}


	@GetMapping("/admin/ingredienteDelete/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {

		Ingrediente ingrediente = ingredienteService.findById(id);
		List<Piatto> piatti = piattoService.findAll();
		for(Piatto p : piatti) {
			if(p.getIngredienti().contains(ingrediente)) {
				p.getIngredienti().remove(ingrediente);
				piattoService.save(p);
			}
		}
		ingredienteService.deleteById(id);
		return "adminindex.html";
	}

	@Transactional
	@PostMapping("/admin/ingredienteEdited/{id}")
	public String editedIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {

		Ingrediente oldingrediente = ingredienteService.findById(id);

		if(!ingrediente.getNome().equals(oldingrediente.getNome()))
			ingredienteValidator.validate(ingrediente, bindingResult);

		if(!bindingResult.hasErrors()) {
		oldingrediente.setNome(ingrediente.getNome());
		oldingrediente.setOrigine(ingrediente.getOrigine());
		oldingrediente.setDescrizione(ingrediente.getDescrizione());

		ingredienteService.save(oldingrediente);
		model.addAttribute("ingrediente", oldingrediente);
		return "ingrediente.html";
		}
		return "ingredienteEdit.html";
	}

	@GetMapping("/admin/ingredienteEdit/{id}")
	public String editIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingredienteEdit.html";
	}

	@GetMapping("/ingredienteList")
	public String getIngredienteList( Model model) {
		List<Ingrediente> ingredienti= ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienteList.html";
	}

	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.findById(id));
		return "ingrediente.html";
	}




}
