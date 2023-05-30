package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }


    public List<Person> getAlls() {
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    public List<Person> getPersonsByAdress(String address) {
        return personRepository.findByAddress(address);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

}
