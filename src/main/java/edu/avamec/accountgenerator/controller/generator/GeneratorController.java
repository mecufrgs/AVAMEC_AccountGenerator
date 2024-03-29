package edu.avamec.accountgenerator.controller.generator;

import edu.avamec.accountgenerator.service.generator.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeneratorController implements IGeneratorController {
    @Autowired
    GeneratorService generatorService;

    @Override
    @CrossOrigin
    @PostMapping("/create_users/{nome}")
    public ResponseEntity<?> createUsers(@PathVariable String nome) {
        if(!nome.isEmpty()){
            return generatorService.criar(nome);
        }
        return new ResponseEntity<>("Nome não pode ser nulo.", HttpStatus.BAD_REQUEST);
    }

}
