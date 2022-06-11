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
	private IngredienteRepository ir;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		ir.save(ingrediente);
	}
	
	@Transactional
	public void deleteById(Long id) {
		ir.deleteById(id);
	}
	
	public Ingrediente findById(Long id) {
		return ir.findById(id).get();
	}

	public boolean alreadyExists(Ingrediente ingrediente) {
		return ir.existsById(ingrediente.getId());
	}

	public List<Ingrediente> findAll() {

		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (Ingrediente i : ir.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	

}
