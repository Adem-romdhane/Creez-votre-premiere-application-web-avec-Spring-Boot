package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("get EMAILS BY CITY");
        return personService.getEmailsByCity(city);
    }

    //http://localhost:8080/v1/api/person/childAlert?address=1509%20Culver%20St
    @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address) {
        log.info("get children info");
        return personService.getChildrenByAddress(address);
    }

    //http://localhost:8080/v1/api/person/personInfo?firstName=Felicia&lastName=Boyd
    @GetMapping("personInfo")
    public List<PersonInfoDTO> getByFirstAndLastName(@RequestParam("firstName") String firstName,
                                                     @RequestParam("lastName") String lastName){
        log.info("get person info by name");

        return personService.findPersonInfoByFirstAndLastName(firstName,lastName);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("add person");
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPerson() {
        log.info("get all persons");
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

}
