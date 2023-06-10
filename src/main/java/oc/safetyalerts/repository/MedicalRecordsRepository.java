package oc.safetyalerts.repository;

import oc.safetyalerts.model.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords,Long> {

    MedicalRecords findByFirstNameAndLastName(String firstName, String lastName);
}
