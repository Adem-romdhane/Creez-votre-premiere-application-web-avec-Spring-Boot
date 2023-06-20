package oc.safetyalerts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildAlertDTO {
    String firstName;
    String lastName;
    String address;
    private String birthdate;

    public int calculateAge() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdateDate;

        try {
            birthdateDate = dateFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // En cas d'erreur lors de la conversion de la date
        }

        Calendar today = Calendar.getInstance();
        Calendar birthdateCalendar = Calendar.getInstance();
        birthdateCalendar.setTime(birthdateDate);

        int age = today.get(Calendar.YEAR) - birthdateCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthdateCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birthdateCalendar.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < birthdateCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }
}
