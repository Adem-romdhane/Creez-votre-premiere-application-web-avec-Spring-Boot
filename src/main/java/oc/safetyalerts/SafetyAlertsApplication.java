package oc.safetyalerts;

import com.google.gson.Gson;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SafetyAlertsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafetyAlertsApplication.class, args);

        Gson gson = new Gson();
        List<Person> persons = null;
        List<FireStations> firestations = null;
        List<MedicalRecords> medicalRecords = null;
        try (FileReader reader = new FileReader("src/main/resources/Data.json")) {
            Data data = gson.fromJson(reader, Data.class);

            // Accédez aux objets et effectuez les opérations souhaitées
            persons = data.getPersons();
            firestations = data.getFirestations();
            medicalRecords = data.getMedicalrecords();

            // Faites quelque chose avec les données

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afficher les informations de toutes les personnes
        System.out.println("Liste des personnes :");
        for (Person person : persons) {
            System.out.println("Nom : " + person.getLastName() + ", Prénom : " + person.getFirstName());
            System.out.println("Adresse : " + person.getAddress());
            System.out.println("Ville : " + person.getCity());
            System.out.println("Code postal : " + person.getZip());
            System.out.println("Téléphone : " + person.getPhone());
            System.out.println("Email : " + person.getEmail());
            System.out.println();
        }

        // Afficher les informations des casernes de pompiers
        System.out.println("Liste des casernes de pompiers :");
        for (FireStations fireStation : firestations) {
            System.out.println("Adresse : " + fireStation.getAddress());
            System.out.println("Numéro de caserne : " + fireStation.getStation());
            System.out.println();
        }

        // Afficher les informations médicales de toutes les personnes
        System.out.println("Liste des informations médicales :");
        for (MedicalRecords medicalRecord : medicalRecords) {
            System.out.println("Nom : " + medicalRecord.getLastName() + ", Prénom : " + medicalRecord.getFirstName());
            System.out.println("Date de naissance : " + medicalRecord.getBirthdate());
            System.out.println("Médications : " + Arrays.toString(medicalRecord.getMedications().toArray()));
            System.out.println("Allergies : " + Arrays.toString(medicalRecord.getAllergies().toArray()));
            System.out.println();
        }
    }

    @lombok.Data
    public class Data {
        private List<Person> persons;
        private List<FireStations> firestations;
        private List<MedicalRecords> medicalrecords;

        // Getter and setter methods
    }

}

