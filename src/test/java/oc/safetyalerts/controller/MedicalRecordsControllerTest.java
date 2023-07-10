package oc.safetyalerts.controller;

import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsControllerTest {

    @Mock
    private MedicalRecordsService medicalRecordsService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;




    @Test
    public void testGetPersonByAddress() throws Exception {
        // Prepare mock data
        String address = "123 Main St";
        List<PersonFireAddressDTO> personList = Arrays.asList(
                new PersonFireAddressDTO("John", "Doe", "123 Main St"),
                new PersonFireAddressDTO("Jane", "Smith", "123 Main St")
        );

        // Configure mock service method
        when(personService.getPeopleByAddress(eq(address))).thenReturn(personList);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/medicalrecord/fire")
                        .param("address", address))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(personList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(personList.get(0).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value(personList.get(0).getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value(personList.get(1).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value(personList.get(1).getLastName()));

        // Verify mock interactions
        verify(personService, times(1)).getPeopleByAddress(eq(address));
    }



}