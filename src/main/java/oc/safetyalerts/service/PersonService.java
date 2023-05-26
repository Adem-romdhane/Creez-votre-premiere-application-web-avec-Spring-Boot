package oc.safetyalerts.service;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public List<Person> getAlls(){
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    public List<Person> getPersonsByAdress(String adress){
        return personRepository.findByAdress(adress);
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Person getById(Long id){
       return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(Person person){
         personRepository.delete(person);
    }
}
