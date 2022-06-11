package it.uniroma3.siw.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CredenzialiController {

    @Autowired
    private CredenzialiService credenzialiService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("credenziali", new Credenziali());
        return "register.html";
    }

    @PostMapping("/register")
    public String register (@Valid @ModelAttribute("credenziali") Credenziali credenziali, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            this.credenzialiService.saveCredenziali(credenziali);
            return "login.html";
        }
            model.addAttribute("credenziali", credenziali);
            return "register.html";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {

        UserDetails adminDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali credentials = credenzialiService.getCredenziali(adminDetails.getUsername());
        if (credentials.getRole().equals(Credenziali.ADMIN_ROLE)) {
            //return "redirect:/admin";
            return "adminindex.html";
        }
        //return "redirect:/login";
        return "allindex.html";
    }

    @GetMapping("/allindex")
    public String getAllindex(Model model) {
        return "allindex.html";
    }

    @GetMapping("/adminindex")
    public String getAdminindex(Model model) {
        return "adminindex.html";
    }

}