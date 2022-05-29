package it.uniroma3.siw.siwcatering.controller;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Ingradiente;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.IngradienteService;
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
    private PiattoService piattoService;

    @Autowired
    private IngradienteService ingradienteService;

    @Autowired
    private PiattoValidator piattoValidator;



    @PostMapping("/piatto")
    public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, Model model, BindingResult bindingResult) {

        piattoValidator.validate(piatto, bindingResult);

        if(!bindingResult.hasErrors()) {
            piattoService.save(piatto);
            model.addAttribute("piatto", piatto);
            model.addAttribute("ingradienti", piatto.getIngradienti());
            return "piatto.html";
        }
        model.addAttribute("ingradienti", ingradienteService.findAll());
        return "piattoForm.html";
    }

    @GetMapping("/piattoForm")
    public String getPiattoForm(Model model) {
        model.addAttribute("piatto", new Piatto());
        model.addAttribute("ingradienti", ingradienteService.findAll());
        return "piattoForm.html";
    }


    @GetMapping("/piattoDelete/{id}")
    public String deletePiatto(@PathVariable("id") Long id, Model model) {

        piattoService.deleteById(id);

        return "index.html";

    }


    @GetMapping("/piattoModify/{id}")
    public String modifyPiatto(@PathVariable("id") Long id, Model model) {
        Piatto piatto = piattoService.findById(id);
        model.addAttribute("piatto", piatto);
        return "piattoForm.html";
    }


    @GetMapping("/piattoList")
    public String getPiattoList( Model model) {
        List<Piatto> piatti= piattoService.findAll();
        model.addAttribute("piatti", piatti);
        return "piattoList.html";
    }

    @GetMapping("/piatto/{id}")
    public String getPiatto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("piatto", piattoService.findById(id));
        return "piatto.html";
    }


}
