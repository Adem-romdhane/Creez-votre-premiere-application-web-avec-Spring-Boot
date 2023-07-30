package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.MedicalRecordsRepository;
import oc.safetyalerts.service.MedicalRecordsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MedicalRecordsController.class)
class MedicalRecordsControllerTest {

    @MockBean
    private MedicalRecordsService medicalRecordsService;

    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setup() {
        // Configure the behavior of medicalRecordsService mock
        Mockito.when(medicalRecordsService.addMedicalRecord(Mockito.any(MedicalRecords.class)))
                .thenReturn(new MedicalRecords());
    }
    @Test
     void testAddMedicalRecord() throws Exception {
        // Créer un objet MedicalRecords pour la requête
        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setFirstName("John");
        medicalRecords.setLastName("Boyd");
        medicalRecords.setBirthdate("03/06/1984");
        medicalRecords.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecords.setAllergies(Arrays.asList("nillacilan"));

        // Définir le comportement attendu du service mocké
        Mockito.when(medicalRecordsService.addMedicalRecord(Mockito.any(MedicalRecords.class)))
                .thenReturn(medicalRecords);

        // Effectuer la requête POST avec les données JSON
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/medicalrecord/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Boyd"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("03/06/1984"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications[0]").value("aznol:350mg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications[1]").value("hydrapermazol:100mg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies[0]").value("nillacilan"));

        // Vérifier que la méthode du service a été appelée avec les bonnes données
        Mockito.verify(medicalRecordsService).addMedicalRecord(Mockito.any(MedicalRecords.class));
    }




    @Test
     void testGetPersonByAddress() throws Exception {
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
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(personList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(personList.get(0).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value(personList.get(0).getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value(personList.get(1).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value(personList.get(1).getLastName()));

        // Verify mock interactions
        verify(personService, times(1)).getPeopleByAddress(eq(address));
    }

    /*@Test
     void testUpdateMedicalRecords() throws Exception {
        //given
        MedicalRecords savedMedicalRecords = new MedicalRecords();
        savedMedicalRecords.setFirstName("adem");
        savedMedicalRecords.setLastName("rmdhn");
        savedMedicalRecords.setBirthdate("1/2/1420");
        savedMedicalRecords.setMedications(Arrays.asList("doliprane","smecta"));
        savedMedicalRecords.setAllergies(Arrays.asList("dust"));
        medicalRecordsRepository.save(savedMedicalRecords);

        MedicalRecords update = new MedicalRecords();
        update.setFirstName("adam");
        update.setLastName("romdhane");
        update.setBirthdate("1/2/1420");
        update.setMedications(Arrays.asList("doliprane","smecta"));
        update.setAllergies(Arrays.asList("dust"));
        medicalRecordsRepository.save(update);

        ResultActions response = mockMvc.perform(put("/v1/api/medicalrecord/{firstName}/{lastName}", savedMedicalRecords.getFirstName(), savedMedicalRecords.getLastName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)));

        response.andExpect(status().isOk());
    }*/










        }