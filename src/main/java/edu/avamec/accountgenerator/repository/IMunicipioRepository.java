package edu.avamec.accountgenerator.repository;

import edu.avamec.accountgenerator.data.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMunicipioRepository extends CrudRepository<Municipio, Long> {
    Municipio findMunicipioById(Long id);
}
