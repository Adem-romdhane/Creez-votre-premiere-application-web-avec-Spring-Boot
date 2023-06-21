package oc.safetyalerts.controller;

import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import oc.safetyalerts.service.dto.PersonInfoDTO;
import oc.safetyalerts.service.dto.PersonStationDTO;
import oc.safetyalerts.service.endPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
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

    //http://localhost:8080/v1/api/firestation?stationNumber=2
    @GetMapping("/firestation")
    public List<PersonStationDTO> getPeopleByFireStation(@RequestParam("stationNumber") int stationNumber) {
        return personService.findByStationNumber(stationNumber);
    }

    //http://localhost:8080/v1/api/phoneAlert?stationNumber=2
    @GetMapping("/phoneAlert")
    public List<String> getPhone(@RequestParam("stationNumber") int stationNumber) {
        return personService.findPhoneByStationNumber(stationNumber);
    }

    //http://localhost:8080/v1/api/personInfo?firstName=John&lastName=Boyd
    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return personService.findPersonInfoByFirstAndLastName(firstName, lastName);
    }

    //http://localhost:8080/v1/api/fire?address=951%20LoneTree%20Rd
    @GetMapping("/fire")
    public List<PersonFireAddressDTO> getPersonByAddress(@RequestParam("address") String address) {
        return personService.getPeopleByAddress(address);
    }

    //http://localhost:8080/communityEmail?city=%3Ccity
    @GetMapping("/communityEmail")
    public List<String> getCommunityEmail(@RequestParam("city") String city) {
        return personService.getEmailsByCity(city);
    }


  /*  @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address) {
        return personService.getChildrenByAddress(address);
    }*/

}
