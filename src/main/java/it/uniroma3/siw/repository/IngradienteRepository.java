package it.uniroma3.siw.repository;


import it.uniroma3.siw.model.Ingradiente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngradienteRepository extends CrudRepository<Ingradiente, Long> {

}