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


    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/persons")
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @PutMapping("update/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person person){
        Person updatePerson = personRepository.findById(id).get();
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setAddress(person.getAddress());
        updatePerson.setCity(person.getCity());
        updatePerson.setZip(person.getZip());
        updatePerson.setEmail(person.getEmail());
        updatePerson.setPhone(person.getPhone());

        return "Update....";
    }

    @DeleteMapping
    public void deletePerson(@RequestBody Person person){
        personRepository.delete(person);
    }

}
