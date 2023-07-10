package oc.safetyalerts.service.mapper;

import org.springframework.stereotype.Component;

@Component
public class FloodMapper {

   /* public FloodDTO toDto(Person person, MedicalRecords medicalRecords)*//*{
        FloodDTO dto = new FloodDTO();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setPhone(person.getPhone());
        dto.setMedications(medicalRecords.getMedications());
        dto.setAllergies(medicalRecords.getAllergies());
        String birthdateStr = medicalRecords.getBirthdate();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthdate, currentDate).getYears();
        dto.setAge(age);
        return dto;
    }*/
}
