package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MedicalRecordsRepository implements IMedicalRecordsRepository{
    @Override
    public MedicalRecords findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public MedicalRecords save(MedicalRecords updateMedical) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<MedicalRecords> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<MedicalRecords> findAll() {
        return null;
    }
}
