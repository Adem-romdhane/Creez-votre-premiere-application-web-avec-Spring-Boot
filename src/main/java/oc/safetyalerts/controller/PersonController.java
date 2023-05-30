package oc.safetyalerts.controller;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    private List<Person> persons = new ArrayList<>(); // Liste de personnes (exemple)


    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson (@RequestBody Person person)
    {
        Person person1= personRepository.save(person);

        return new ResponseEntity<>(person1,HttpStatus.OK);
    }


    // Endpoint GET pour rechercher une personne par son adresse
    @GetMapping("/persons/address/{address}")
    public Person getPersonByAddress(@PathVariable String address) {
       return (Person) personRepository.findByAddress(address);
    }


    @GetMapping(value = "/persons/all")
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    // Get a Person by his Id
    @GetMapping("/getPersonById/{id}")
    public Optional<Person> findPersonById(@PathVariable Long id){
        return personRepository.findById(id);
    }

    //Get Person By his address
    @GetMapping("/getPersonByAddress/{address}")
    public List<Person> findPersonByAdress(@PathVariable String address){
        return personRepository.findByAddress(address);
    }

    //Get Person By his firstname and lastname
    @GetMapping("/getPersonByName")
    public List<Person> findPersonByAddress(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName ){
        return personRepository.findByFirstNameAndLastName(firstName,lastName);
    }


    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatePerson = personRepository.findById(id).get();
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setAddress(person.getAddress());
        updatePerson.setCity(person.getCity());
        updatePerson.setZip(person.getZip());
        updatePerson.setEmail(person.getEmail());
        updatePerson.setPhone(person.getPhone());

        personRepository.save(updatePerson);

        return "Updated...";
    }

    @DeleteMapping("/deletePersonById/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable Long id) {
        personRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
