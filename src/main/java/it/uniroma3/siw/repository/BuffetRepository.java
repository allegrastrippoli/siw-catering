package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Buffet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	public boolean existsByNome(String nome);
}
