package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/medicalrecord")
@RequiredArgsConstructor
public class MedicalRecordsController {

    @Autowired
    PersonService personService;


    MedicalRecordsService medicalRecordsService;
    List<MedicalRecords> medicalRecords = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<MedicalRecords>> getAllMedicalRecords() {
        return new ResponseEntity<>(medicalRecordsService.getAll(), HttpStatus.OK);
    }

    //http://localhost:8080/v1/api/medicalrecord/fire?address=951%20LoneTree%20Rd
    @GetMapping("/fire")
    public List<PersonFireAddressDTO> getPersonByAddress(@RequestParam("address") String address) {
        return personService.getPeopleByAddress(address);
    }

    @PostMapping
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return new ResponseEntity<>(medicalRecordsService.addMedicalRecord(medicalRecords), HttpStatus.OK);
    }




}
