package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import oc.safetyalerts.service.dto.PersonInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/person")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    PersonService personService;

    private List<Person> personList = new ArrayList<Person>(); // Liste de personnes (simulant une base de données)

    public PersonController(PersonService personService) {
        this.personService=personService;
    }

    // http://localhost:8080/v1/api/person/communityEmail?city=Culver
    @GetMapping("/communityEmail")
    public List<String> getEmailsByCity(@RequestParam("city") String city) {
        return personService.getEmailsByCity(city);
    }

    //http://localhost:8080/v1/api/person/childAlert?address=1509%20Culver%20St
    @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address) {
        return personService.getChildrenByAddress(address);
    }

    //http://localhost:8080/v1/api/person/personInfo?firstName=Felicia&lastName=Boyd
    @GetMapping("personInfo")
    public List<PersonInfoDTO> getByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return personService.findPersonInfoByFirstAndLastName(firstName,lastName);
    }


    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPerson() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }


  /*  @PutMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> updatePerson(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody Person updatedPerson) {

        for (Person person : personList) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                person.setAddress(updatedPerson.getAddress());
                person.setCity(updatedPerson.getCity());
                person.setZip(updatedPerson.getZip());
                person.setPhone(updatedPerson.getPhone());
                person.setEmail(updatedPerson.getEmail());
                return ResponseEntity.ok("Personne mise à jour avec succès");
            }
        }

        return ResponseEntity.notFound().build();
    }
*/
  /*  @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {

        for (Person person : personList) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                personList.remove(person);
                return ResponseEntity.ok("Personne supprimée avec succès");
            }
        }

        return ResponseEntity.notFound().build();
    }*/
}
