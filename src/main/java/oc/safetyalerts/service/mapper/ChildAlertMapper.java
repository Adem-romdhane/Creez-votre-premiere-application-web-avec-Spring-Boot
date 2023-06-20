package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import org.springframework.stereotype.Component;

@Component
public class ChildAlertMapper {

    public ChildAlertDTO toDto (MedicalRecords medicalRecords){
        ChildAlertDTO dto = new ChildAlertDTO();
        dto.setFirstName(medicalRecords.getFirstName());
        dto.setLastName(medicalRecords.getLastName());
        dto.setBirthdate(medicalRecords.getBirthdate());

        return dto;
    }


}
