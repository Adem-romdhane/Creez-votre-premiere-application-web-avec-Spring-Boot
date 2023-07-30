package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.IFireStationsRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.FloodDTO;
import oc.safetyalerts.service.dto.PersonInfoMedicalDTO;
import oc.safetyalerts.service.dto.PersonStationDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerTest {

    @Autowired
    private FireStationsController fireStationsController;
    @Mock
    private FireStationsService fireStationsService;
    @MockBean
    private PersonService personService;
    @Mock
    private IFireStationsRepository fireStationsRepository;

    @Mock
    private JsonData jsonData;

    @Autowired
    private MockMvc mockMvc;




    @Test
     void testAddFireStations() {
        // Mocking the service and repository
        FireStationsService fireStationsService = mock(FireStationsService.class);
        FireStationsRepository fireStationsRepository = mock(FireStationsRepository.class);

        // Creating a sample person
        FireStations fireStations = new FireStations();
        fireStations.setStation(1);
        fireStations.setAddress("10 street");

        // Mocking the repository method
        when(fireStationsRepository.save(fireStations)).thenReturn(fireStations);

        // Creating the controller instance
        FireStationsController fireStationsController = new FireStationsController(fireStationsService);

        // Mocking the service method
        when(fireStationsService.addFireStations(fireStations)).thenReturn(fireStations);

        // Calling the controller method
        ResponseEntity<FireStations> responseEntity = fireStationsController.addFireStations(fireStations);

        // Verifying the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(fireStations, responseEntity.getBody());
    }

    @Test
     void testFindAllFireStations() {
        // Mocking the service and repository
        FireStationsService fireStationsService = mock(FireStationsService.class);
        FireStationsRepository fireStationsRepository = mock(FireStationsRepository.class);

        // Creating sample data
        List<FireStations> expectedFireStations = new ArrayList<>();
        expectedFireStations.add(new FireStations());

        // Mocking the repository method
        when(fireStationsRepository.findAll()).thenReturn(expectedFireStations);

        // Creating the controller instance
        FireStationsController fireStationsController = new FireStationsController(fireStationsService);

        // Mocking the service method
        when(fireStationsService.getAll()).thenReturn(expectedFireStations);

        // Calling the controller method
        ResponseEntity<List<FireStations>> responseEntity = fireStationsController.findAllFireStations();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFireStations, responseEntity.getBody());
    }


    @Test
     void testgetPhoneByStationNumber() throws Exception {
        // Prepare mock data
        int stationNumber = 1;
        List<String> phoneList = asList("123-456-7890", "987-654-3210");

        // Configure mock service method
        when(personService.findPhoneByStationNumber(eq(stationNumber))).thenReturn(phoneList);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/firestations/phoneAlert").param("stationNumber", String.valueOf(stationNumber))).andExpect(status().isOk());


        // Verify mock interactions
        verify(personService, times(1)).findPhoneByStationNumber(eq(stationNumber));
    }


    @Test
     void testGetFloodStations() throws Exception {
        // Données de test
        List<Integer> stationNumbers = Arrays.asList(1, 2, 3);

        // Résultat attendu
        List<FloodDTO> expectedFloodStations = new ArrayList<>();

        FloodDTO floodDTO1 = new FloodDTO();
        floodDTO1.setAddress("123 Main St");

        PersonInfoMedicalDTO personInfoMedicalDTO1 = new PersonInfoMedicalDTO();
        personInfoMedicalDTO1.setPhone("1234567890");
        MedicalRecords medicalRecords1 = new MedicalRecords();
        medicalRecords1.setFirstName("John");
        medicalRecords1.setLastName("Boyd");
        medicalRecords1.setBirthdate("03/06/1984");
        medicalRecords1.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecords1.setAllergies(Arrays.asList("nillacilan"));
        personInfoMedicalDTO1.setMedicalRecords(medicalRecords1);

        floodDTO1.setPersonInfoMedicalDTOS(Arrays.asList(personInfoMedicalDTO1));

        FloodDTO floodDTO2 = new FloodDTO();
        floodDTO2.setAddress("456 Elm St");

        PersonInfoMedicalDTO personInfoMedicalDTO2 = new PersonInfoMedicalDTO();
        personInfoMedicalDTO2.setPhone("9876543210");
        MedicalRecords medicalRecords2 = new MedicalRecords();
        medicalRecords2.setFirstName("Jacob");
        medicalRecords2.setLastName("Boyd");
        medicalRecords2.setBirthdate("03/06/1989");
        medicalRecords2.setMedications(Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"));
        medicalRecords2.setAllergies(new ArrayList<>());
        personInfoMedicalDTO2.setMedicalRecords(medicalRecords2);

        floodDTO2.setPersonInfoMedicalDTOS(Arrays.asList(personInfoMedicalDTO2));

        expectedFloodStations.add(floodDTO1);
        expectedFloodStations.add(floodDTO2);

        // Mock du service
        when(personService.getFloodStations(stationNumbers)).thenReturn(expectedFloodStations);

        // Exécution de la requête
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/firestations/flood/stations")
                        .param("stations", "1,2,3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Ajoutez d'autres assertions pour vérifier le contenu de la réponse si nécessaire
    }


    @Test
     void testGetPeopleByFireStation() throws Exception {
        // Données de test
        int stationNumber = 123;

        // Résultat attendu
        List<PersonStationDTO> expectedPeople = Arrays.asList(
                new PersonStationDTO("John", "Doe", "123 Main St", "123-456-7890"),
                new PersonStationDTO("Jane", "Smith", "456 Elm St", "987-654-3210")
        );

        // Mock du service
        when(personService.findByStationNumber(anyInt())).thenReturn(expectedPeople);

        // Exécution de la requête
        mockMvc.perform(get("/v1/api/firestations/firestation")
                        .param("stationNumber", String.valueOf(stationNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedPeople.size()))
                .andExpect(jsonPath("$[0].firstName").value(expectedPeople.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(expectedPeople.get(0).getLastName()))
                .andExpect(jsonPath("$[0].address").value(expectedPeople.get(0).getAddress()))
                .andExpect(jsonPath("$[0].phone").value(expectedPeople.get(0).getPhone()))
                .andExpect(jsonPath("$[1].firstName").value(expectedPeople.get(1).getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(expectedPeople.get(1).getLastName()))
                .andExpect(jsonPath("$[1].address").value(expectedPeople.get(1).getAddress()))
                .andExpect(jsonPath("$[1].phone").value(expectedPeople.get(1).getPhone()));
        // Ajoutez d'autres assertions pour vérifier le contenu de la réponse si nécessaire
    }

    @Test
    @Disabled
     void testDeleteStationByAddress_Success() throws Exception {
        // Données de test
        String address = "123 Main St";

        // Mock du service
        FireStations fireStations = new FireStations();
        when(fireStationsService.findByAddress(address)).thenReturn(fireStations);

        // Exécution de la requête
        mockMvc.perform(delete("/v1/api/firestations/{address}", address))
                .andExpect(status().isNoContent());

        // Vérification du service
        verify(fireStationsService, times(1)).deleteFireStationsByAddress(address);
    }
    @Test
     void testDeleteStationByAddress_NotFound() throws Exception {
        // Données de test
        String address = "123 Main St";

        // Mock du service
        when(fireStationsService.findByAddress(address)).thenReturn(null);

        // Exécution de la requête
        mockMvc.perform(delete("/v1/api/firestations/{address}", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}