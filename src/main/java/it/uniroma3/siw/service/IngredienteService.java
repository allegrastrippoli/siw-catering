package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id);
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	public boolean alreadyExists(Ingrediente ingrediente) {
		return ingredienteRepository.existsByNome(ingrediente.getNome());
	}

	public List<Ingrediente> findAll() {

		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (Ingrediente i : ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}

}
