package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.PersonStationDTO;
import oc.safetyalerts.service.endPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    JsonData jsonService;

    @GetMapping("/firestation")
    public List<PersonStationDTO> getPeopleByFireStation(@RequestParam("stationNumber") int stationNumber) {
        return personService.findByStationNumber(stationNumber);
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

    //http://localhost:8080/communityEmail?city=%3Ccity
    @GetMapping("/communityEmail")
    public List<String> getCommunityEmail(@RequestParam("city") String city) {
        List<String> emails = new ArrayList<>();

        for (Person person : jsonService.getPersons()) {
            if (person.getCity().equalsIgnoreCase(city)) {
                emails.add(person.getEmail());
            }
        }

        return emails;
    }
}
