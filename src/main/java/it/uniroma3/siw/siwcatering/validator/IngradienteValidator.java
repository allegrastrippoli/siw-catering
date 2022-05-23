package it.uniroma3.siw.siwcatering.validator;

import it.uniroma3.siw.siwcatering.model.Ingradiente;
import it.uniroma3.siw.siwcatering.service.IngradienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IngradienteValidator implements Validator {

	@Autowired 
	private IngradienteService is;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ingradiente.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (is.alreadyExists((Ingradiente)o))
			errors.reject("ingradiente.duplicato");
		
	}

	
}
