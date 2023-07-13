package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MedicalRecordsRepository implements IMedicalRecordsRepository{

    List<MedicalRecords> medicalRecordsList = new ArrayList<>();
    @Override
    public MedicalRecords findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public MedicalRecords save(MedicalRecords medicalRecords) {
        medicalRecordsList.add(medicalRecords);
        return medicalRecords;
    }



    @Override
    public Optional<MedicalRecords> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<MedicalRecords> findAll() {
        return null;
    }

    @Override
    public void delete(MedicalRecords medicalRecord) {

        medicalRecordsList.remove(medicalRecord);
    }
}
