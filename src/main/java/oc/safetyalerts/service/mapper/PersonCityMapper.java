package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonCityDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonCityMapper {

    public PersonCityDTO toDto (Person person){
        PersonCityDTO dto = new PersonCityDTO();

        dto.setEmail(person.getEmail());
        return dto;
    }
}
