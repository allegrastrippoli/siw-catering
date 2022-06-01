package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Piatto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiattoRepository extends CrudRepository<Piatto, Long> {
	public boolean existsByNome(String nome);
}
