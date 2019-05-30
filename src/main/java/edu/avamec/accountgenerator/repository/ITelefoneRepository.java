package edu.avamec.accountgenerator.repository;

import edu.avamec.accountgenerator.data.Telefone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITelefoneRepository extends CrudRepository<Telefone, Long> { }
