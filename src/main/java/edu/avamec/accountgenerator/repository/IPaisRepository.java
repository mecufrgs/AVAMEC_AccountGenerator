package edu.avamec.accountgenerator.repository;

import edu.avamec.accountgenerator.data.Pais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepository extends CrudRepository<Pais, Long> {
    Pais findPaisById(Long id);
}
