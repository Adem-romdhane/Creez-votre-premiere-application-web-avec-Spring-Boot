package oc.safetyalerts.controller;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MedicalRecordsController {

    @Autowired
    MedicalRecordsRepository medicalRecordsRepository;

    //find all is ok
    @GetMapping("/findAllMedical")
    public List<MedicalRecords> getAllMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }

    // Add is OK
    @PostMapping("/addNewMedicalRecords")
    public MedicalRecords save(@RequestBody MedicalRecords medicalRecords) {

      return  medicalRecordsRepository.save(medicalRecords);
    }

    //UPDATE METHOD IS OK
    @PutMapping(value = "/updateMedicalRecordsById/{id}")
    public String updateMedicalRecords(@PathVariable Long id, @RequestBody MedicalRecords medicalRecords) {
        MedicalRecords updateMedicalRecords = medicalRecordsRepository.findById(id).get();
        updateMedicalRecords.setFirstName(medicalRecords.getFirstName());
        updateMedicalRecords.setLastName(medicalRecords.getLastName());
        updateMedicalRecords.setMedications(medicalRecords.getMedications());
        updateMedicalRecords.setAllergies(medicalRecords.getAllergies());
        updateMedicalRecords.setBirthdate(medicalRecords.getBirthdate());

        medicalRecordsRepository.save(updateMedicalRecords);

        return "Updated successfully";
    }

    //delete medical records OK
    @DeleteMapping(value = "/deleteMedicalRecordsById/{id}")
    public void deleteMedicalRecords(@PathVariable Long id) {
        medicalRecordsRepository.deleteById(id);
    }

}
