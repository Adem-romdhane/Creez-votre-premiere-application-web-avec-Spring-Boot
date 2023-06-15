package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oc.safetyalerts.model.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCityDTO {

    String city;
    String email;

    public PersonCityDTO toDto(Person person) {
        PersonCityDTO dto = new PersonCityDTO();
        dto.setCity(person.getCity());
        dto.setEmail(person.getEmail());

        return dto;
    }
}
