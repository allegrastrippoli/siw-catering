package it.uniroma3.siw.validator;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class PiattoValidator implements Validator {

    @Autowired
    private PiattoService piattoService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Piatto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(piattoService.alreadyExists((Piatto)o))
            errors.reject("piatto.duplicato");
    }
}
