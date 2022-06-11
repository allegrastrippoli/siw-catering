package it.uniroma3.siw.validator;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.service.BuffetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class BuffetValidator implements Validator {

    @Autowired
    private BuffetService buffetService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Buffet.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(buffetService.alreadyExists((Buffet)o))
            errors.reject("buffet.duplicato");
    }
}
