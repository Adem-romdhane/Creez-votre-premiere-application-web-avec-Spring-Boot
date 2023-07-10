package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.repository.PersonRepository;
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

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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


    @Test
    public void testAddPerson() {
        // Mocking the service and repository
        PersonService personService = mock(PersonService.class);
        PersonRepository personRepository = mock(PersonRepository.class);

        // Creating a sample person
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");

        // Mocking the repository method
        when(personRepository.save(person)).thenReturn(person);

        // Creating the controller instance
        PersonController personController = new PersonController(personService);

        // Mocking the service method
        when(personService.addPerson(person)).thenReturn(person);

        // Calling the controller method
        ResponseEntity<Person> responseEntity = personController.addPerson(person);

        // Verifying the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(person, responseEntity.getBody());
    }

 /* @Test
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