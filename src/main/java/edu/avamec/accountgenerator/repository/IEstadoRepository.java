package edu.avamec.accountgenerator.repository;

import edu.avamec.accountgenerator.data.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoRepository extends CrudRepository<Estado, Long> {
    Estado findEstadoById(Long id);
}
