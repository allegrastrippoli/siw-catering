package it.uniroma3.siw.siwcatering.validator;

import it.uniroma3.siw.siwcatering.model.Persona;
import it.uniroma3.siw.siwcatering.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonaValidator implements Validator {

	@Autowired
	private PersonaService ps;

	
	public boolean supports(Class<?> clazz) {
		return Persona.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (ps.alreadyExists((Persona)o)) {
			errors.reject("persona.duplicato");
		}

	}

	
	

}
