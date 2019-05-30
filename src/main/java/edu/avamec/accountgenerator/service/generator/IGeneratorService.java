package edu.avamec.accountgenerator.service.generator;

import org.springframework.http.ResponseEntity;

public interface IGeneratorService {

    ResponseEntity<?> criar(String nomeGerador);
}
