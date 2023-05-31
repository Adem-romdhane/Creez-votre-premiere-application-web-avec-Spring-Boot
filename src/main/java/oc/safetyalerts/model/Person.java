package oc.safetyalerts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor // constructeur sans argument
@AllArgsConstructor // constructeur avec arguments
@ToString // methode to string
@Entity
@Table(name = "Person")
public class Person {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;



}
