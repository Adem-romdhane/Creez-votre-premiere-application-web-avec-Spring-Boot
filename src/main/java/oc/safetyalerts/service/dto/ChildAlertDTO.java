package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildAlertDTO {
    String firstName;
    String lastName;
    int age;
    List<MedicalRecords> houseMembers;

    public <T> ChildAlertDTO(String tenley, String boyd, int i, List<T> asList, ChildAlertDTO childAlertDTO) {
    }
}
