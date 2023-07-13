package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.IMedicalRecordsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordsService {


    private final IMedicalRecordsRepository medicalRecordsRepository;

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();

    public List<MedicalRecords> getAll(){return medicalRecordsRepository.findAll();}


    public MedicalRecords addMedicalRecord(MedicalRecords medicalRecords) {
        return medicalRecordsRepository.save(medicalRecords);
    }

    public void deleteMedicalRecordsByFirstAndLastName(String firstName, String lastName) {
        MedicalRecords medicalRecord = medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName);
        if (medicalRecord != null) {
            medicalRecordsRepository.delete(medicalRecord);
        }
    }

    public MedicalRecords updateMedical(MedicalRecords medicalRecords) {
        MedicalRecords updateMedical = new MedicalRecords();
        updateMedical.setFirstName(medicalRecords.getFirstName());
        updateMedical.setLastName(medicalRecords.getLastName());
        updateMedical.setBirthdate(medicalRecords.getBirthdate());
        updateMedical.setMedications(medicalRecords.getMedications());
        updateMedical.setAllergies(medicalRecords.getAllergies());

        return medicalRecordsRepository.save(updateMedical);
    }





}