package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public List<Person> getPersonsByAddress(String address) {
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

    public Person getByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Person updatePerson(Person person) {
        Person updatePerson = new Person();
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setAddress(person.getAddress());
        updatePerson.setCity(person.getCity());
        updatePerson.setZip(person.getZip());
        updatePerson.setEmail(person.getEmail());
        updatePerson.setPhone(person.getPhone());

        return personRepository.save(updatePerson);

    }

    public List<Person> findByAddressIn(List<String> addresses) {
        return PersonRepository.findByAddressIn(addresses);
    }
}
