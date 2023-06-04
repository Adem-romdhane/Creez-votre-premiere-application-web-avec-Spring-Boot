package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.service.MedicalRecordsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsControllerTest {

    @Mock
    private MedicalRecordsService medicalRecordsService;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<MedicalRecords>  medicalRecordsCaptor;

    @InjectMocks
    private MedicalRecordsController medicalRecordsController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test //Test cette méthode getAllMedicalRecords
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/v1/api/medicalrecord")).
                andExpect(status().isOk());
    }

    @Test
    public void testAddMedicalRecord() throws Exception {
        // Préparation des données de test
        MedicalRecords medicalRecord = new MedicalRecords();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("1990-01-01");
        medicalRecord.setMedications(Arrays.asList("Med1", "Med2"));
        medicalRecord.setAllergies(Arrays.asList("Allergy1", "Allergy2"));

        // Définition du comportement du service mocké
        when(medicalRecordsService.addMedicalRecord(any(MedicalRecords.class))).thenReturn(medicalRecord);

        // Exécution de la requête POST
        ResultActions result = mockMvc.perform(post("/v1/api/medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)));

        // Vérification des résultats
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(medicalRecord.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(medicalRecord.getLastName()))
                .andExpect(jsonPath("$.birthdate").value(medicalRecord.getBirthdate()))
                .andExpect(jsonPath("$.medications").isArray())
                .andExpect(jsonPath("$.medications.length()").value(medicalRecord.getMedications().size()))
                .andExpect(jsonPath("$.medications[0]").value(medicalRecord.getMedications().get(0)))
                .andExpect(jsonPath("$.medications[1]").value(medicalRecord.getMedications().get(1)))
                .andExpect(jsonPath("$.allergies").isArray())
                .andExpect(jsonPath("$.allergies.length()").value(medicalRecord.getAllergies().size()))
                .andExpect(jsonPath("$.allergies[0]").value(medicalRecord.getAllergies().get(0)))
                .andExpect(jsonPath("$.allergies[1]").value(medicalRecord.getAllergies().get(1)));


    }

    @Test
    public void testDeleteMedicalRecordById() throws Exception {
        Long medicalRecordId = 1L;

        when(medicalRecordsService.getById(medicalRecordId)).thenReturn(null);

        mockMvc.perform(delete("/v1/api/medicalrecord/{id}", medicalRecordId))
                .andExpect(status().isNotFound());

        verify(medicalRecordsService, never()).deleteMedicalRecord(any(MedicalRecords.class));
    }

    @Test
    public void testDeleteMedicalRecords() {
        // Préparation
        Long id = 1L;
        // Initialisez le dossier médical existant ou null
        MedicalRecords medicalRecordsFinded = new MedicalRecords();

        when(medicalRecordsService.getById(id)).thenReturn(medicalRecordsFinded);

        // Exécution
        ResponseEntity<Void> response = medicalRecordsController.deleteMedicalRecords(id);

        // Vérifications
        if (medicalRecordsFinded == null) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Vérifiez que le code de statut est "404 Not Found"
            verify(medicalRecordsService, never()).deleteMedicalRecord(any(MedicalRecords.class)); // Vérifiez que la méthode deleteMedicalRecord n'a pas été appelée
        } else {
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Vérifiez que le code de statut est "204 No Content"
            verify(medicalRecordsService, times(1)).deleteMedicalRecord(medicalRecordsFinded); // Vérifiez que la méthode deleteMedicalRecord a été appelée avec le dossier médical trouvé
        }
    }
}