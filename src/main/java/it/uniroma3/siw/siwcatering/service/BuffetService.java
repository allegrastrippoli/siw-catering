package it.uniroma3.siw.siwcatering.service;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository br;


    @Transactional
    public void save(Buffet buffet) { br.save(buffet); }

    @Transactional
    public void deleteById(Long id) {
        br.deleteById(id);
    }


    public Buffet findById(Long id) {

        return br.findById(id).get();
    }

    public boolean alreadyExists(Buffet buffet) {
        return br.existsByNome(buffet.getNome());

    }

    public List<Buffet> findAll() {

        List<Buffet> buffets = new ArrayList<Buffet>();
        for (Buffet b : br.findAll()) {
            buffets.add(b);
        }
        return buffets;
    }



}
