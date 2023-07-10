package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoMedicalDTO {

    private String phone;
    private MedicalRecords medicalRecords;

}
