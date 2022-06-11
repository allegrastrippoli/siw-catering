package it.uniroma3.siw.validator;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IngredienteValidator implements Validator {

	@Autowired 
	private IngredienteService ingredienteService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (ingredienteService.alreadyExists((Ingrediente)o))
			errors.reject("ingrediente.duplicato");
		
	}

	
}
