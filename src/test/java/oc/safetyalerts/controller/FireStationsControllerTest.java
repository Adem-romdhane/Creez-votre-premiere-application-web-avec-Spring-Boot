package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.IFireStationsRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testFindAllFireStations() {
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
    public void testgetPhoneByStationNumber()throws Exception{
        // Prepare mock data
        int stationNumber=1;
        List<String> phoneList=asList("123-456-7890","987-654-3210");

        // Configure mock service method
        when(personService.findPhoneByStationNumber(eq(stationNumber))).thenReturn(phoneList);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/firestations/phoneAlert")
                        .param("stationNumber",String.valueOf(stationNumber)))
                .andExpect(status().isOk());


        // Verify mock interactions
        verify(personService,times(1)).findPhoneByStationNumber(eq(stationNumber));
    }

}