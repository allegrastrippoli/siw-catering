package it.uniroma3.siw.siwcatering.repository;

import it.uniroma3.siw.siwcatering.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

	
	public boolean existsByNomeAndCognomeAndEta(String nome, String cognome, Integer eta);
}
