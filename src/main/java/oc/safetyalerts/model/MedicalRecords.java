package oc.safetyalerts.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor // constructeur sans argument
@AllArgsConstructor // constructeur avec argument
@ToString // methode to string
@Entity
@Table(name = "MedicalRecords")
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String LastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

}
