package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloodDTO {
 private String address;
 private List<PersonInfoMedicalDTO> personInfoMedicalDTOS;
}

