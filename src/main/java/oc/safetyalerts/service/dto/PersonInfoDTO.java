package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int age;
    private List<String> medications;
    private List<String> allergies;
}


