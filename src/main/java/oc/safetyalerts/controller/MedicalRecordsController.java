package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.MedicalRecordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/medicalrecord")
@RequiredArgsConstructor
public class MedicalRecordsController {


    private final MedicalRecordsService medicalRecordsService;
    List<MedicalRecords> medicalRecords = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<MedicalRecords>> getAllMedicalRecords() {
        return new ResponseEntity<>(medicalRecordsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return new ResponseEntity<>(medicalRecordsService.addMedicalRecord(medicalRecords), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecords> updateMedicalRecords(@PathVariable Long id, @RequestBody MedicalRecords medicalRecords) {
        MedicalRecords medicalRecordsFinded = medicalRecordsService.getById(id);
        if (medicalRecordsFinded == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicalRecordsService.updateMedical(medicalRecords), HttpStatus.CREATED);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMedicalRecords(@PathVariable Long id) {
        MedicalRecords medicalRecordsFinded = medicalRecordsService.getById(id);
        if (medicalRecordsFinded == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medicalRecordsService.DeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
