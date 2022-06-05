package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.IngradienteService;
import it.uniroma3.siw.service.PiattoService;
import it.uniroma3.siw.validator.PiattoValidator;
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
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private IngradienteService ingradienteService;

    @Autowired
    private PiattoValidator piattoValidator;



    @PostMapping("/admin/piatto")
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

    @GetMapping("/admin/piattoForm")
    public String getPiattoForm(Model model) {
        model.addAttribute("piatto", new Piatto());
        model.addAttribute("ingradienti", ingradienteService.findAll());
        return "piattoForm.html";
    }


    @GetMapping("/admin/piattoDelete/{id}")
    public String deletePiatto(@PathVariable("id") Long id, Model model) {
        piattoService.deleteById(id);
        return "adminindex.html";
    }


    @Transactional
    @PostMapping("/admin/piattoEdited/{id}")
    public String editedPiatto(@PathVariable("id") Long id, @Valid @ModelAttribute Piatto piatto, Model model, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()) {

            Piatto oldPiatto = piattoService.findById(id);
            oldPiatto.setNome(oldPiatto.getNome());
            oldPiatto.setDescrizione(oldPiatto.getDescrizione());
            oldPiatto.setIngradienti(oldPiatto.getIngradienti());

            piattoService.save(piatto);
            model.addAttribute("piatto", piatto);

            return "piatto.html";
        }
        return "piattoEdit.html";
    }


    @GetMapping("/admin/piattoEdit/{id}")
    public String editPiatto(@PathVariable("id") Long id, Model model) {
        Piatto piatto = piattoService.findById(id);
        model.addAttribute("piatto", piatto);
        model.addAttribute("ingradienti", ingradienteService.findAll());
        return "piattoEdit.html";
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
