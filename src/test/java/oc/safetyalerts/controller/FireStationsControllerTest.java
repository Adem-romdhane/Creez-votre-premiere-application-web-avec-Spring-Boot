package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.service.FireStationsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerTest {

    @Mock
    private FireStationsService fireStationsService;

    @MockBean
    private FireStationsRepository fireStationsRepository;

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

    @Test
    @Disabled
    void getById() throws Exception {
        FireStations fireStations = new FireStations();
        fireStations.setId(1L);
        fireStations.setAddress("rue de verdun");

        when(fireStationsService.getById(anyLong())).thenReturn(fireStations);
      //  mockMvc.perform(get())
           //     .andExpect(status().isOk());
    }

    @Test
    public void DeleteFireStationById () throws Exception {
        Long stationId = 1L;

        when(fireStationsService.getById(stationId)).thenReturn(null);

        mockMvc.perform(delete("/v1/api/firestations/{id}", stationId))
                .andExpect(status().isNotFound());

        verify(fireStationsService, never()).deleteFireStation(any(FireStations.class));
    }

    @Test
    void saveFireStationTest() {
        FireStations fireStations = new FireStations(null,
                "rue de verdun",3);
        when(fireStationsService.savedFireStation(fireStations)).thenReturn(fireStations);
        assertEquals(fireStations, fireStationsService.savedFireStation(fireStations));
    }

    @Test
    public void testSave() throws Exception {
        FireStations fireStations = new FireStations();
        fireStations.setId(1L);
        // Set other necessary fields

        when(fireStationsService.savedFireStation(any(FireStations.class))).thenReturn(fireStations);

        mockMvc.perform(post("/v1/api/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStations)))
                .andExpect(status().isCreated());

        verify(fireStationsService).savedFireStation(any(FireStations.class));
    }

}