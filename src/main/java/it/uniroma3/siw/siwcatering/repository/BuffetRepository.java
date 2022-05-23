package it.uniroma3.siw.siwcatering.repository;

import it.uniroma3.siw.siwcatering.model.Buffet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	public boolean existsByNome(String nome);
}
