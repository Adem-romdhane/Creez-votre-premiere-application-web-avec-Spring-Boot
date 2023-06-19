package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.IFireStationsRepository;
import oc.safetyalerts.service.FireStationsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerTest {

    @Mock
    private FireStationsService fireStationsService;

    @MockBean
    private IFireStationsRepository fireStationsRepository;

    @InjectMocks
    private FireStationsController fireStationsController;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test // test  @GetMapping(value = "/persons/all") from personController
    public void testGetAllFireStations() throws Exception {
        mockMvc.perform(get("/v1/api/firestations")).
                andExpect(status().isOk());
    }

    @Test
    public void testAddFireStation() throws Exception {
        FireStations fireStation = new FireStations();
        fireStation.setId(null);
        fireStation.setAddress("123 Main St");
        fireStation.setStation(1);

        when(fireStationsService.savedFireStation(fireStation)).thenReturn(fireStation);
        assertEquals(fireStation, fireStationsService.savedFireStation(fireStation));
    }

   /* @Test

    void getById() throws Exception {
        FireStations fireStations = new FireStations();
        fireStations.setId(1L);
        fireStations.setAddress("rue de verdun");

        when(fireStationsService.getById(anyLong())).thenReturn(fireStations);
      //  mockMvc.perform(get())
           //     .andExpect(status().isOk());
    }*/

    @Test
    @Disabled
    public void DeleteFireStationById () throws Exception {
        Long stationId = 1L;

        when(fireStationsService.getById(stationId)).thenReturn(null);

        mockMvc.perform(delete("/v1/api/firestations/{id}", stationId))
                .andExpect(status().isNotFound());

        verify(fireStationsService, never()).deleteFireStationsById(stationId);
    }

    @Test
    void saveFireStationTest() {
        FireStations fireStations = new FireStations();
        fireStations.setId(null);
        fireStations.setStation(1);
        fireStations.setAddress("rue due verdun");
        when(fireStationsService.savedFireStation(fireStations)).thenReturn(fireStations);
        assertEquals(fireStations, fireStationsService.savedFireStation(fireStations));
    }

    @Test
    void testDeleteStationById() {
        // Définir l'ID fictif de la fire station à supprimer
        Long fireStationId = 1L;

        // Créer un objet FireStations fictif pour les tests
        FireStations mockFireStations = new FireStations();
        mockFireStations.setId(fireStationId);
        mockFireStations.setAddress("Station 1");

        // Configurer le comportement du service pour retourner l'objet FireStations fictif
        when(fireStationsService.getById(fireStationId)).thenReturn(mockFireStations);

        // Appeler la méthode deleteStationById du controller
        ResponseEntity<Void> responseEntity = fireStationsController.deleteStationById(fireStationId);

        // Vérifier que la méthode getById du service a été appelée avec le bon ID
        verify(fireStationsService).getById(fireStationId);

        // Vérifier que la méthode deleteFireStationsById du service a été appelée avec le bon ID
        verify(fireStationsService).deleteFireStationsById(fireStationId);

        // Vérifier que la réponse renvoie un code de statut HTTP NO_CONTENT (204)
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testSaveFireStation() throws Exception {
        // Créer un objet FireStations fictif pour les tests
        FireStations mockFireStations = new FireStations();
        mockFireStations.setId(1L);
        mockFireStations.setAddress("Station 1");

        // Configurer le comportement du service pour retourner l'objet FireStations fictif
        when(fireStationsService.savedFireStation(mockFireStations)).thenReturn(mockFireStations);

        // Convertir l'objet FireStations en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationsJson = objectMapper.writeValueAsString(mockFireStations);

        // Effectuer la requête POST en envoyant l'objet FireStations JSON
        mockMvc.perform(post("/v1/api/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationsJson))
                .andExpect(status().isCreated());
    }
}