package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonStationDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public void setIsAdult(boolean isAdult) {

    }

    public boolean isAdult() {
        LocalDate currentDate = LocalDate.now();
        CharSequence birthdate = null;
        LocalDate birthdateDate = LocalDate.parse(birthdate);

        int age = Period.between(birthdateDate, currentDate).getYears();

        return age >= 18;
    }
}
