package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonStationDTO;
import org.springframework.stereotype.Component;

@Component
public class PersionStationMapper {
    // MAPPING
    public PersonStationDTO toDto(Person person){
        PersonStationDTO dto = new PersonStationDTO();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setPhone(person.getPhone());
        return dto;
    }
}
