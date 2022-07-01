package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    @Transactional
    public void save(Chef chef) { chefRepository.save(chef); }

    @Transactional
    public void deleteById(Long id) {
        chefRepository.deleteById(id);
    }

    /* findById e findAll non sono @Transactional perche' richiedono solo lettura */
    public Chef findById(Long id) {

        return chefRepository.findById(id).get();
    }


    /* il metodo e' stato implementato in modo da non rendere possibile l'inserimento di due persone che hanno stesso nome, cognome, eta' */
    public boolean alreadyExists(Chef chef) {
        return chefRepository.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());

    }

    public List<Chef> findAll() {

        List<Chef> chefs=new ArrayList<Chef>();
        for(Chef c : chefRepository.findAll()) {
            chefs.add(c);
        }
        return chefs;
    }

    public List<Chef> findByNomeOrderByCognomeAsc(String nome) {
        return chefRepository.findByNomeOrderByCognomeAsc(nome);
    }

    public List<Chef> findAllByOrderByCognomeAsc() {
        return chefRepository.findAllByOrderByCognomeAsc();
    }

    public long Count() {
        return chefRepository.count();
    }


}