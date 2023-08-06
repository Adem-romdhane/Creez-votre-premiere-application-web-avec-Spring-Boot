package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.IMedicalRecordsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordsService {
    private final IMedicalRecordsRepository medicalRecordsRepository;

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    public List<MedicalRecords> getAll(){
        log.info("get all medical records");
        return medicalRecordsRepository.findAll();}

    public MedicalRecords addMedicalRecord(MedicalRecords medicalRecords) {
        log.info("save medical records");
        return medicalRecordsRepository.save(medicalRecords);
    }

    public void deleteMedicalRecordsByFirstAndLastName(String firstName, String lastName) {

        MedicalRecords medicalRecord = medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName);
        if (medicalRecord != null) {
            log.info("deleted successfully");
            medicalRecordsRepository.delete(medicalRecord);
        }
        log.error("not deleted ");
    }

    public MedicalRecords updateMedical(MedicalRecords medicalRecords) {
        log.info("updated successfully");
        MedicalRecords updateMedical = new MedicalRecords();
        updateMedical.setFirstName(medicalRecords.getFirstName());
        updateMedical.setLastName(medicalRecords.getLastName());
        updateMedical.setBirthdate(medicalRecords.getBirthdate());
        updateMedical.setMedications(medicalRecords.getMedications());
        updateMedical.setAllergies(medicalRecords.getAllergies());

        return medicalRecordsRepository.save(updateMedical);
    }

}