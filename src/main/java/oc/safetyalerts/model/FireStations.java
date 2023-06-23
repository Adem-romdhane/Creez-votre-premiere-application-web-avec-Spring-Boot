package oc.safetyalerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import oc.safetyalerts.service.dto.PersonStationDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FireStations {


    private String address;
    private int station;

    private List<PersonStationDTO> personDTOS;
    private List<Person> personList;

}
