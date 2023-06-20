package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonInfoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class PersonInfoMapper {

    public PersonInfoDTO toDto (Person person , MedicalRecords medicalRecords){
        PersonInfoDTO dto = new PersonInfoDTO();
        dto.setFirstName(medicalRecords.getFirstName());
        dto.setLastName(medicalRecords.getLastName());
        dto.setEmail(person.getEmail());
        dto.setAddress(person.getAddress());
        dto.setAllergies(medicalRecords.getAllergies());
        dto.setMedications(medicalRecords.getMedications());
        String birthdateStr = medicalRecords.getBirthdate();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthdate, currentDate).getYears();
        dto.setAge(age);
        return dto;
    }

}
