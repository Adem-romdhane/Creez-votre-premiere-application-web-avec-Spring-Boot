package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordsService {


    private final IMedicalRecordsRepository medicalRecordsRepository;

    public List<MedicalRecords> getAll(){return medicalRecordsRepository.findAll();}


    public MedicalRecords addMedicalRecord(MedicalRecords medicalRecords) {
        return medicalRecordsRepository.save(medicalRecords);
    }

    public MedicalRecords getById(Long id) {
        return medicalRecordsRepository.findById(id).orElse(null);
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

    public void DeleteById( Long id){
        medicalRecordsRepository.deleteById(id);
    }

    public MedicalRecords findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordsRepository.findByFirstNameAndLastName(firstName,lastName);
    }
}