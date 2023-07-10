package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Mock
    private PersonService personService;
    @MockBean
    private FireStationsService fireStationsService;
    @Mock
    private JsonData jsonData;

    @Mock
    private IPersonRepository personRepository;
    @Autowired
    private MockMvc mockMvc;


 /*   @Test
    public void testGetChildAlert() throws Exception {
        // Créer les données de test
        String address = "1509 Culver St";
        List<ChildAlertDTO> childAlertList = new ArrayList<>();

        // Mock du service
        when(personService.getChildrenByAddress(address)).thenReturn(childAlertList);

        // Effectuer la requête HTTP GET et vérifier la réponse
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/person/childAlert")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


        // Vérifier l'appel au service
        verify(personService).getChildrenByAddress(address);
    }*/


    @Test
    void getAllPersonsTest() {
        when(personService.getAll()).thenReturn(Stream.
                of(new Person(
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getAll().size());
    }


    @Test
    void deletePersonTest() {
        Person person = (new Person(
                "aaaa",
                "bbbb",
                "antony",
                "paris",
                "89", "89065454", "aaa@yahoo.com"));
        personService.deletePerson(person);
        // verify(personService, times(1)).deletePerson(person);
    }


}