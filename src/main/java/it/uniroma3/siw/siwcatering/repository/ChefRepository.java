package it.uniroma3.siw.siwcatering.repository;

import it.uniroma3.siw.siwcatering.model.Chef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends CrudRepository<Chef, Long> {
	public boolean existsByNomeAndCognomeAndNazionalita(String nome, String cognome, String nazionalita);
}
