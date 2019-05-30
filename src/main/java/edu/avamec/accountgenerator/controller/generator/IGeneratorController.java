package edu.avamec.accountgenerator.controller.generator;

import org.springframework.http.ResponseEntity;

public interface IGeneratorController {

    ResponseEntity<?> createUsers(String name);

}
