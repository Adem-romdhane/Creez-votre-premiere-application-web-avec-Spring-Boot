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

    @Autowired
    MedicalRecordsService medicalRecordsService;

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecords>> getAllMedicalRecords() {
        return new ResponseEntity<>(medicalRecordsService.getAll(), HttpStatus.OK);
    }

    //http://localhost:8080/v1/api/medicalrecord/fire?address=951%20LoneTree%20Rd
    @GetMapping("/fire")
    public List<PersonFireAddressDTO> getPersonByAddress(@RequestParam("address") String address) {
        return personService.getPeopleByAddress(address);
    }

    @PostMapping("/add")
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return new ResponseEntity<>(medicalRecordsService.addMedicalRecord(medicalRecords), HttpStatus.OK);
    }

    @PutMapping("{firstName}/{lastName}")
    public ResponseEntity<MedicalRecords> updateMedicalRecord(
            @PathVariable("lastName") String lastName,
            @PathVariable("firstName") String firstName,
            @RequestBody MedicalRecords updateRecords
    ) {
        for (MedicalRecords medicalRecords : medicalRecordsList) {
            if (medicalRecords.getFirstName().equals(firstName) && medicalRecords.getLastName().equals(lastName)) {
                medicalRecords.setFirstName(updateRecords.getFirstName());
                medicalRecords.setLastName(updateRecords.getLastName());
                medicalRecords.setBirthdate(updateRecords.getBirthdate());
                medicalRecords.setMedications(updateRecords.getMedications());
                medicalRecords.setAllergies(updateRecords.getAllergies());
                medicalRecordsService.updateMedical(medicalRecords); // Mettre à jour l'enregistrement médical dans le service
                return ResponseEntity.ok(medicalRecords); // Retourner l'enregistrement médical mis à jour
            }
        }

        return ResponseEntity.notFound().build(); // Retourner une réponse 404 si l'enregistrement médical n'est pas trouvé
    }

}




