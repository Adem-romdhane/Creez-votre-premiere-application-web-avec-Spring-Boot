package oc.safetyalerts.service;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.IMedicalRecordsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedicalRecordsServiceTest {
    @MockBean
    private IMedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
     void testDeleteMedicalRecordsByFirstAndLastName() {
        // Données de test
        String firstName = "John";
        String lastName = "Doe";
        MedicalRecords medicalRecord = new MedicalRecords();
        medicalRecord.setFirstName(firstName);
        medicalRecord.setLastName(lastName);

        // Mock du comportement du repository
        when(medicalRecordsRepository.findByFirstNameAndLastName(firstName, lastName))
                .thenReturn(medicalRecord);

        // Exécution de la méthode à tester
        medicalRecordsService.deleteMedicalRecordsByFirstAndLastName(firstName, lastName);

        // Vérification de l'appel à la méthode de suppression du repository
        verify(medicalRecordsRepository, times(1)).delete(medicalRecord);
    }


    @Test
     void testAddMedicalRecord() {
        // Données de test
        MedicalRecords medicalRecord = new MedicalRecords();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecord.setAllergies(Arrays.asList("nillacilan"));

        // Mock du comportement du repository
        when(medicalRecordsRepository.save(any(MedicalRecords.class))).thenReturn(medicalRecord);

        // Appel de la méthode à tester
        MedicalRecords result = medicalRecordsService.addMedicalRecord(medicalRecord);

        // Vérification du comportement du repository
        verify(medicalRecordsRepository, times(1)).save(medicalRecord);

        // Vérification du résultat
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
        assertEquals("03/06/1984", result.getBirthdate());
        assertEquals(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), result.getMedications());
        assertEquals(Arrays.asList("nillacilan"), result.getAllergies());
    }
    @Test
     void testUpdateMedical() {
        // Préparation des données de test
        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setFirstName("John");
        medicalRecords.setLastName("Boyd");
        medicalRecords.setBirthdate("03/06/1984");
        medicalRecords.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecords.setAllergies(Arrays.asList("nillacilan"));

        MedicalRecords updatedMedicalRecords = new MedicalRecords();
        updatedMedicalRecords.setFirstName("John");
        updatedMedicalRecords.setLastName("Boyd");
        updatedMedicalRecords.setBirthdate("03/06/1984");
        updatedMedicalRecords.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        updatedMedicalRecords.setAllergies(Arrays.asList("nillacilan"));

        when(medicalRecordsRepository.save(updatedMedicalRecords)).thenReturn(updatedMedicalRecords);

        // Appel de la méthode à tester
        MedicalRecords result = medicalRecordsService.updateMedical(medicalRecords);

        // Vérification du résultat
        assertEquals(updatedMedicalRecords, result);
        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
        // Ajoutez d'autres assertions pour les autres attributs modifiés

        // Vérification que la méthode save du repository mocké a été appelée avec les données mises à jour
        verify(medicalRecordsRepository).save(updatedMedicalRecords);
    }

    @Test
    void testFindAll(){
        MedicalRecords medicalRecords1 = new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg"), Arrays.asList("hydrapermazol:100mg"));
        MedicalRecords medicalRecords2 = new MedicalRecords("jack", "Boaaa", "03/06/1984", Arrays.asList("aznol:350mg"), Arrays.asList("hydrapermazol:100mg"));
        List<MedicalRecords> medicalRecordsList = Arrays.asList(medicalRecords1,medicalRecords2);

        when(medicalRecordsRepository.findAll()).thenReturn(medicalRecordsList);

        List<MedicalRecords> all = medicalRecordsService.getAll();

        assertEquals(2,all.size());

    }


}