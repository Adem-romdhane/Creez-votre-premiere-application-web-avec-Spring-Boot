package oc.safetyalerts.controller;

import oc.safetyalerts.model.Personne;
import oc.safetyalerts.repository.PersonneRepository;
import oc.safetyalerts.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonneController {

    @Autowired
    PersonneRepository personneRepository;


    @PostMapping("/person")
    Personne personne(@RequestBody Personne personne) {
        return personneRepository.save(personne);
    }

}
