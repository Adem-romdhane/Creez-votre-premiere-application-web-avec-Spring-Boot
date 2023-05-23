package oc.safetyalerts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor // constructeur sans argument
@AllArgsConstructor // constructeur avec argument
@ToString // methode to string
@Entity
public class Personne {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String LastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
}
