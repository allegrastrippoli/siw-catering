package it.uniroma3.siw.siwcatering.service;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChefService {

    @Autowired
    private ChefRepository pr;

    @Transactional
    public void save(Chef chef) { pr.save(chef); }

    @Transactional
    public void deleteById(Long id) {
        pr.deleteById(id);
    }

    /* findById e findAll non sono @Transactional perche' richiedono solo lettura */
    public Chef findById(Long id) {

        return pr.findById(id).get();
    }


    /* il metodo e' stato implementato in modo da non rendere possibile l'inserimento di due persone che hanno stesso nome, cognome, eta' */
    public boolean alreadyExists(Chef chef) {
        return pr.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());

    }

    public List<Chef> findAll() {

        List<Chef> chefs=new ArrayList<Chef>();
        for(Chef c : pr.findAll()) {
            chefs.add(c);
        }
        return chefs;
    }




}
