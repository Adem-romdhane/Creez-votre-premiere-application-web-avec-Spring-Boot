package oc.safetyalerts.repository;

import oc.safetyalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPersonRepository {

    List<Person> findByAddress(String address);

    Person findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findByAddressIn(List<String> addresses);

    List<Person> findAll();
    Person save(Person person);
    Person delete(Person person);

    List<Person> findByStationNumber(int stationNumber);



    List<String> getEmailsByCity(List<Person> persons, String city);
}
