package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FireStationDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int adultCount;
    private int childCount;
}
