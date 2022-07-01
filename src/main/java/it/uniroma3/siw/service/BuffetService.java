package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;


    @Transactional
    public void save(Buffet buffet) { buffetRepository.save(buffet); }

    @Transactional
    public void deleteById(Long id) {
        buffetRepository.deleteById(id);
    }


    public Buffet findById(Long id) {

        return buffetRepository.findById(id).get();
    }

    public boolean alreadyExists(Buffet buffet) {
        return buffetRepository.existsByNome(buffet.getNome());

    }

    public List<Buffet> findAll() {

        List<Buffet> buffets = new ArrayList<Buffet>();
        for (Buffet b : buffetRepository.findAll()) {
            buffets.add(b);
        }
        return buffets;
    }

    public List<Buffet> findAllByOrderByChefAsc() {
        return buffetRepository.findAllByOrderByChefAsc();
    }

    public long count() { return buffetRepository.count(); }



}
