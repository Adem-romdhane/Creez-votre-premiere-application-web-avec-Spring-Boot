package oc.safetyalerts.controller;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;


    @PostMapping
    public Person save(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> findAllPerson(){
        return personRepository.findAll();
    }

}
