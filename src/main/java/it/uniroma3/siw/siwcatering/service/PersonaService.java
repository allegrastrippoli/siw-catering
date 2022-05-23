package it.uniroma3.siw.siwcatering.service;

import it.uniroma3.siw.siwcatering.model.Persona;
import it.uniroma3.siw.siwcatering.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository pr;
	
	@Transactional
	public void save(Persona persona) {

		pr.save(persona);
	}
	
	/* findById e findAll non sono @Transactional perche' richiedono solo lettura */
	public Persona findById(Long id) {
		
		return pr.findById(id).get();
	}
	
	public List<Persona> findAll() {
		
		 List<Persona> persone = new ArrayList<Persona>();
		 
		 for(Persona p : pr.findAll())
			 persone.add(p);
		
		return persone;
	}
	
	/* il metodo e' stato implementato in modo da non rendere possibile l'inserimento di due persone che hanno stesso nome, cognome, eta' */
	public boolean alreadyExists(Persona persona) {
		
		/*  return pr.existsByNomeAndCognomeAndEta(persona.getNome(), persona.getCognome(), persona.getEta());  */
		return pr.existsById(persona.getId());
		
	}
	
	@Transactional
	public void deleteById(Long id) {
		pr.deleteById(id);
	}


}
