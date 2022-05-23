package it.uniroma3.siw.siwcatering.validator;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class BuffetValidator implements Validator {

    @Autowired
    private BuffetService bs;

    @Override
    public boolean supports(Class<?> clazz) {
        return Buffet.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(bs.alreadyExists((Buffet)o))
            errors.reject("buffet.duplicato");
    }
}
