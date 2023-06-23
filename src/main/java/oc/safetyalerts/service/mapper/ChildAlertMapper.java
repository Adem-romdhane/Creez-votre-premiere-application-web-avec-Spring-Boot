package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class ChildAlertMapper {

    public ChildAlertDTO toDto( MedicalRecords medicalRecords) {
        ChildAlertDTO dto = new ChildAlertDTO();
        dto.setFirstName(medicalRecords.getFirstName());
        dto.setLastName(medicalRecords.getLastName());
        // CONVERT A BIRTHDATE IN A AGE INTEGER
        String birthdateStr = medicalRecords.getBirthdate();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthdate, currentDate).getYears();
        dto.setAge(age);

        return dto;
    }


}
