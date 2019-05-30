package edu.avamec.accountgenerator.repository;

import edu.avamec.accountgenerator.data.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findFirstByNomeGeradorOrderByNumeroGeradorDesc(String nomeGerador);

}
