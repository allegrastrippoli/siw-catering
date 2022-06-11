package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;


    @Transactional
    public void save(Piatto piatto) { piattoRepository.save(piatto); }

    @Transactional
    public void deleteById(Long id) {
        piattoRepository.deleteById(id);
    }


    public Piatto findById(Long id) {

        return piattoRepository.findById(id).get();
    }

    public boolean alreadyExists(Piatto piatto) {
        return piattoRepository.existsByNome(piatto.getNome());

    }

    public List<Piatto> findAll() {

        List<Piatto> piatti = new ArrayList<Piatto>();
        for (Piatto p : piattoRepository.findAll()) {
            piatti.add(p);
        }
        return piatti;
    }

}
