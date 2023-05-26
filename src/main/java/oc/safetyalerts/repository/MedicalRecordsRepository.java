package oc.safetyalerts.repository;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords,Long> {


}
