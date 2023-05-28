package oc.safetyalerts.controller;

import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerTest {

    @Autowired
    private FireStationsService fireStationsService;

    @MockBean
    private FireStationsRepository fireStationsRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test // test  @GetMapping(value = "/persons/all") from personController
    public void testGetAllFireStations() throws Exception {
        mockMvc.perform(get("/firestations/all")).
                andExpect(status().isOk());
    }

}