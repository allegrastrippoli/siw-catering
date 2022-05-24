package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.model.Ingradiente;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.PiattoService;
import it.uniroma3.siw.siwcatering.validator.PiattoValidator;
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
public class PiattoController {

    @Autowired
    private PiattoService ps;

    @Autowired
    private PiattoValidator pv;

    @PostMapping("/piatto")
    public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, Model model, BindingResult bindingResult) {

        pv.validate(piatto, bindingResult);

        if(!bindingResult.hasErrors()) {
            ps.save(piatto);
            model.addAttribute("piatto", piatto);
            return "piatto.html";
        }
        return "piattoForm.html";
    }

    @GetMapping("/piattoForm")
    public String getPiatto(Model model) {
        model.addAttribute("piatto", new Piatto());
        return "piattoForm.html";
    }

    @GetMapping("/piattoDelete/{id}")
    public String deletePiatto(@PathVariable("id") Long id, Model model) {

        ps.deleteById(id);

        return "index.html";

    }


    @GetMapping("/piattoModify/{id}")
    public String modifyPiatto(@PathVariable("id") Long id, Model model) {
        Piatto piatto = ps.findById(id);
        model.addAttribute("piatto", piatto);
        return "piattoForm.html";
    }


    @GetMapping("/piattoList")
    public String getPiattoList( Model model) {
        List<Piatto> piatto= ps.findAll();
        model.addAttribute("piatto", piatto);
        return "piattoList.html";
    }


	/*
	@GetMapping("/piattoVisualizePiatti")
	public String getPiatti( Model model) {
		List<Piatto>piatti= ps.findAll();
		model.addAttribute("piatti", piatti);
		return "PiattiList.html";
	}
	*/
}
