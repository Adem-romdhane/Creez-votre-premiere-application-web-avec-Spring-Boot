package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PersonRepository implements IPersonRepository{

    private final JsonData jsonData;
    @Override
    public List<Person> findByAddress(String address) {
        return null;
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Person> findByAddressIn(List<String> addresses) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return jsonData.getPersons();
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Person delete(Person person) {
        return null;
    }

    @Override
    public List<Person> findByStationNumber(int stationNumber) {

        List<Person> personByStationNumber = new ArrayList<>();
        List<FireStations> fireStations = jsonData.getFirestations();
        List<FireStations> stations = fireStations.stream()
                .filter(station -> station.getStation() == stationNumber)
                .collect(Collectors.toList());
        List<Person> people = jsonData.getPersons();
        for (Person person : people) {
            for (FireStations fireStations1 : stations) {
                if (person.getAddress().equals(fireStations1.getAddress())) {
                    personByStationNumber.add(person);
                }
            }
        }
        return personByStationNumber;
    }
}
