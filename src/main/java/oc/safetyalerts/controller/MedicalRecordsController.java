package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MedicalRecordsController {

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordsService medicalRecordsService;

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }
    @GetMapping
    public ResponseEntity<List<MedicalRecords>> getAllMedicalRecords() {
        log.info("get all medical records");
        return new ResponseEntity<>(medicalRecordsService.getAll(), HttpStatus.OK);
    }

    //http://localhost:8080/v1/api/medicalrecord/fire?address=951%20LoneTree%20Rd
    @GetMapping("/fire")
    public List<PersonFireAddressDTO> getPersonByAddress(@RequestParam("address") String address) {
        log.info("get person by address");
        return personService.getPeopleByAddress(address);
    }
    @PostMapping("/add")
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        log.info("add medical records");
        return new ResponseEntity<>(medicalRecordsService.addMedicalRecord(medicalRecords), HttpStatus.OK);
    }

}




