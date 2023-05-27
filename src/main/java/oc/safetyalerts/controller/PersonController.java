package oc.safetyalerts.controller;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @GetMapping(value = "/")
    public String getPage() {
        return "TEST";
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping(value = "/persons/all")
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @PutMapping("update/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatePerson = personRepository.findById(id).get();
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setAddress(person.getAddress());
        updatePerson.setCity(person.getCity());
        updatePerson.setZip(person.getZip());
        updatePerson.setEmail(person.getEmail());
        updatePerson.setPhone(person.getPhone());

        return "Updated...";
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Person person) {
        personService.deletePerson(person);
    }


}
