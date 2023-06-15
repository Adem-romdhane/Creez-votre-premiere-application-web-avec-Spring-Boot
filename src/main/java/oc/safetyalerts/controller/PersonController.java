package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/person")
@RequiredArgsConstructor
public class PersonController {


    private final PersonService personService;

    private List<Person> persons = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<Person>> getPersonByAddress(@PathVariable String address) {
        return new ResponseEntity<>(personService.getPersonsByAddress(address), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPerson() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
     //   return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
        return null;
    }

    @GetMapping("/getPersonByName")
    public ResponseEntity<Person> findPersonByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return new ResponseEntity<>(personService.getByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person personFinded = personService.getById(id);
        if (personFinded == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.updatePerson(personFinded);
        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.CREATED);
    }*/

  /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long id) {
        Person personFinded = personService.getById(id);
        if (personFinded == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personService.deletePerson(personFinded);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
