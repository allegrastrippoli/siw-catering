package it.uniroma3.siw.siwcatering.validator;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.ChefService;
import it.uniroma3.siw.siwcatering.validator.ChefValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService ps;

	
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (ps.alreadyExists((Chef)o)) {
			errors.reject("chef.duplicato");
		}

	}

	
	

}
