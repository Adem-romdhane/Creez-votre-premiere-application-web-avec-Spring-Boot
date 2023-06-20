package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonPhoneDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonPhoneMapper {
    public PersonPhoneDTO toDto (Person person){
        PersonPhoneDTO dto = new PersonPhoneDTO();
        dto.setPhone(person.getPhone());
        return dto;
    }
}
