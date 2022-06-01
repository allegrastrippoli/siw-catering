package it.uniroma3.siw.authentication;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenzialiRepository extends CrudRepository<Credenziali, Long> {
    public Optional<Credenziali> findByUsername(String username);
}
