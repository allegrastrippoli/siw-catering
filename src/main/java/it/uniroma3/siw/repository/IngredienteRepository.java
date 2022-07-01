package it.uniroma3.siw.repository;


import it.uniroma3.siw.model.Ingrediente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

    public boolean existsByNome(String nome);

}