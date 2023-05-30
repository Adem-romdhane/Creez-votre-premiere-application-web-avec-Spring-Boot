package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerTest {

    @Autowired
    private FireStationsService fireStationsService;

    @MockBean
    private FireStationsRepository fireStationsRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test // test  @GetMapping(value = "/persons/all") from personController
    public void testGetAllFireStations() throws Exception {
        mockMvc.perform(get("/firestations/all")).
                andExpect(status().isOk());
    }

    @Test
    public void testAddFireStation() throws Exception {
        FireStations fireStation = new FireStations();
        fireStation.setId(null);
        fireStation.setAddress("123 Main St");
        fireStation.setStation(1);

        fireStationsRepository.save(fireStation);

        mockMvc.perform(post("/addFireStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isOk());
    }

}