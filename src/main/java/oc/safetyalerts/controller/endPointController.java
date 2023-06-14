package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonStationDTO;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.endPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class endPointController {
    @Autowired
    FireStationsService fireStationsService;

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordsService medicalRecordsService;

    @Autowired
    endPointService endPointService;

    @GetMapping("/firestation")
    public List<PersonStationDTO> getPeopleByFireStation(@RequestParam("stationNumber") int stationNumber) {
        List<PersonStationDTO> people = new ArrayList<>();

        // Obtient les adresses correspondant au numéro de station
        List<FireStations> fireStations = Collections.singletonList(fireStationsService.findByStationNumber(stationNumber));
        List<String> addresses = fireStations.stream().map(FireStations::getAddress).collect(Collectors.toList());

        // Obtient les informations des personnes correspondant aux adresses
        List<Person> persons = personService.findByAddressIn(addresses);

        // Parcourt les personnes et construit les PersonDTO correspondants
        for (Person person : persons) {
            PersonStationDTO personDTO = new PersonStationDTO();
            personDTO.setFirstName(person.getFirstName());
            personDTO.setLastName(person.getLastName());
            personDTO.setAddress(person.getAddress());
            personDTO.setPhone(person.getPhone());

            // Vérifie si la personne est adulte ou enfant
            MedicalRecords medicalRecord = medicalRecordsService.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            if (medicalRecord != null) {
                LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate());
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(birthdate, currentDate).getYears();
                personDTO.setIsAdult(age > 18);
            }

            people.add(personDTO);
        }

        return people;
    }

    @GetMapping("/fireStationInfo")
    public String getFireStationInfo(@RequestParam("stationNumber") int stationNumber) {
        FireStations fireStation = fireStationsService.findByStationNumber(stationNumber);
        if (fireStation != null) {
            return "Station Number: " + fireStation.getStation() +
                    "\nAddress: " + fireStation.getAddress();
        } else {
            return "Station Number not found";
        }
    }
}
