package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FloodDTO {
 private String address;
 private List<PersonInfoMedicalDTO> personInfoMedicalDTOS;
}

