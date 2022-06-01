package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Ingradiente;
import it.uniroma3.siw.repository.IngradienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngradienteService {
	
	@Autowired
	private IngradienteRepository ir;
	
	@Transactional
	public void save(Ingradiente ingradiente) {
		ir.save(ingradiente);
	}
	
	@Transactional
	public void deleteById(Long id) {
		ir.deleteById(id);
	}
	
	public Ingradiente findById(Long id) {
		return ir.findById(id).get();
	}

	public boolean alreadyExists(Ingradiente ingradiente) {
		return ir.existsById(ingradiente.getId());
	}

	public List<Ingradiente> findAll() {

		List<Ingradiente> ingradienti = new ArrayList<Ingradiente>();
		for (Ingradiente i : ir.findAll()) {
			ingradienti.add(i);
		}
		return ingradienti;
	}
	

}
