package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.MedicalRecordsRepository;
import oc.safetyalerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MedicalRecordsController {

    @Autowired
    MedicalRecordsRepository medicalRecordsRepository;

    @GetMapping("/")
    public List<MedicalRecords> getAllMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody MedicalRecords medicalRecords) {
        medicalRecordsRepository.save(medicalRecords);
    }

    @PutMapping(value = "/updateMedical")
    public String updateMedicalRecords(@PathVariable Long id, @RequestBody MedicalRecords medicalRecords) {
        MedicalRecords updateMedicalRecords = medicalRecordsRepository.findById(id).get();
        updateMedicalRecords.setId(medicalRecords.getId());
        updateMedicalRecords.setFirstName(medicalRecords.getFirstName());
        updateMedicalRecords.setLastName(medicalRecords.getLastName());
        updateMedicalRecords.setMedications(medicalRecords.getMedications());
        updateMedicalRecords.setAllergies(medicalRecords.getAllergies());
        updateMedicalRecords.setBirthdate(medicalRecords.getBirthdate());
        return "Updated successfully";
    }

    @DeleteMapping(value = "/deleteMedical")
    public void deleteMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
        medicalRecordsRepository.delete(medicalRecords);
    }

}
