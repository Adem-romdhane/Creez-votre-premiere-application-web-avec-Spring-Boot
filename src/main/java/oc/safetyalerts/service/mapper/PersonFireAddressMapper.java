package oc.safetyalerts.service.mapper;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class PersonFireAddressMapper {
    public PersonFireAddressDTO toDto(Person person, MedicalRecords medicalRecords, FireStations fireStations) {
        PersonFireAddressDTO dto = new PersonFireAddressDTO();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setPhone(person.getPhone());
        dto.setMedications(medicalRecords.getMedications());
        dto.setAllergies(medicalRecords.getAllergies());
        dto.setStation(fireStations.getStation());
        // CONVERT A BIRTHDATE IN A AGE INTEGER
        String birthdateStr = medicalRecords.getBirthdate();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthdate, currentDate).getYears();
        dto.setAge(age);
        return dto;
    }
    }


