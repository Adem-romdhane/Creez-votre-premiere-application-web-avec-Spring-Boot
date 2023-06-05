package oc.safetyalerts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "FireStations")
public class FireStations {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int station;


}
