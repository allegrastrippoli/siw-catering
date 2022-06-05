package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.model.Ingradiente;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.IngradienteService;
import it.uniroma3.siw.service.PiattoService;
import it.uniroma3.siw.validator.IngradienteValidator;
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
public class IngradienteController {

	@Autowired
	private IngradienteService ingradienteService;

	@Autowired
	private IngradienteValidator ingradienteValidator;

	@Autowired
	private PiattoService piattoService;

	@GetMapping("/admin/ingradienteForm")
	public String getIngradienteForm(Model model) {
		model.addAttribute("ingradiente", new Ingradiente());
		return "ingradienteForm.html";
	}


	@PostMapping("/admin/ingradiente")
	public String addIngradiente(@Valid @ModelAttribute("ingradiente") Ingradiente ingradiente, Model model, BindingResult bindingResults) {

		ingradienteValidator.validate(ingradiente, bindingResults);

		if(!bindingResults.hasErrors()) {
			ingradienteService.save(ingradiente);
			model.addAttribute("ingradiente", ingradiente);
			return "ingradiente.html";
		}
		return "ingradienteForm.html";

	}


	@GetMapping("/admin/ingradienteDelete/{id}")
	public String deleteIngradiente(@PathVariable("id") Long id, Model model) {

		Ingradiente ingradiente = ingradienteService.findById(id);
		List<Piatto> piatti = piattoService.findAll();
		for(Piatto p : piatti) {
			if(p.getIngradienti().contains(ingradiente)) {
				p.getIngradienti().remove(ingradiente);
				piattoService.save(p);
			}
		}
		ingradienteService.deleteById(id);
		return "adminindex.html";
	}

	@Transactional
	@PostMapping("/admin/ingradienteEdited/{id}")
	public String editedIngradiente(@PathVariable Long id, @Valid @ModelAttribute("ingradiente") Ingradiente ingradiente, Model model, BindingResult bindingResult) {

		if(!bindingResult.hasErrors()) {
		Ingradiente oldIngradiente = ingradienteService.findById(id);

		oldIngradiente.setNome(ingradiente.getNome());
		oldIngradiente.setOrigine(ingradiente.getOrigine());
		oldIngradiente.setDescrizione(ingradiente.getDescrizione());

		ingradienteService.save(oldIngradiente);
		model.addAttribute("ingradiente", ingradiente);
		return "ingradiente.html";
		}
		return "ingradienteEdit.html";
	}

	@GetMapping("/admin/ingradienteEdit/{id}")
	public String editIngradiente(@PathVariable("id") Long id, Model model) {
		Ingradiente ingradiente = ingradienteService.findById(id);
		model.addAttribute("ingradiente", ingradiente);
		return "ingradienteEdit.html";
	}

	@GetMapping("/ingradienteList")
	public String getIngradienteList( Model model) {
		List<Ingradiente> ingradienti= ingradienteService.findAll();
		model.addAttribute("ingradienti", ingradienti);
		return "ingradienteList.html";
	}

	@GetMapping("/ingradiente/{id}")
	public String getIngradiente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingradiente", ingradienteService.findById(id));
		return "ingradiente.html";
	}




}
