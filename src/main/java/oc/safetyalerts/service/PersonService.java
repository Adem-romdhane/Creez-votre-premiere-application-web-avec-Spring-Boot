package oc.safetyalerts.service;

import lombok.extern.slf4j.Slf4j;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.service.dto.*;
import oc.safetyalerts.service.mapper.PersonStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private final IPersonRepository personRepository;


    private final PersonStationMapper mapper;


    public PersonService(IPersonRepository personRepository, PersonStationMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }



    public List<Person> getAll() {
        log.info("Find all persons");
        return personRepository.findAll();
    }


    public void deletePerson(Person person) {
        personRepository.delete(person);
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


    //test Ok
    public List<PersonStationDTO> findByStationNumber(int stationNumber) {
        log.info("Finded by station number");

        List<Person> personByStationNumber = personRepository.findByStationNumber(stationNumber);
        return personByStationNumber.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public List<String> getEmailsByCity(String city) {
        return personRepository.getEmailsByCity(city);
    }

    public List<String> findPhoneByStationNumber(int stationNumber) {
        log.info("Phone founded by station number");

        return personRepository.findPhoneByStationNumber(stationNumber);
    }


    public List<PersonInfoDTO> findPersonInfoByFirstAndLastName(String firstName, String lastName) {
        log.info("Person founded by first & lastname");
        return personRepository.findPersonInfoByFirstAndLastName(firstName, lastName);
    }

    public List<PersonFireAddressDTO> getPeopleByAddress(String address) {
        log.info("personne obtenu par adresse ");
        return personRepository.getPeopleByAddress(address);
    }

    public List<ChildAlertDTO> getChildrenByAddress(String address) {
        log.info("get children by address");
        return personRepository.getChildAlert(address);
    }

    public List<FloodDTO> getFloodStations(List<Integer> stationNumbers) {
        log.info("get station list by number station");
        return personRepository.getFloodStations(stationNumbers);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}



