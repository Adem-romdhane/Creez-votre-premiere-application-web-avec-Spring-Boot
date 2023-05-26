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

    Person person = new Person();

    public Person getById(Long id){
       return personRepository.findById(id).orElse(null);
    }
}
