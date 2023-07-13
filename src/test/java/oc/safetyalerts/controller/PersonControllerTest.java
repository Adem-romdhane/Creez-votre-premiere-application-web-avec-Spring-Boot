package oc.safetyalerts.controller;


import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import oc.safetyalerts.service.dto.PersonInfoDTO;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private PersonController personController;
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
    public void testUpdatePerson() {
        // Données de test
        String firstName = "John";
        String lastName = "Boyd";
        Person updatedPerson = new Person();
        updatedPerson.setAddress("1509 Culver St");
        updatedPerson.setCity("Culver");
        updatedPerson.setZip("97451");
        updatedPerson.setPhone("841-874-6512");
        updatedPerson.setEmail("jaboyd@email.com");

        // Exécution de la méthode à tester
        ResponseEntity<String> response = personController.updatePerson(firstName, lastName, updatedPerson);

        // Vérification de la réponse
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personne mise à jour avec succès", response.getBody());
    }

    @Test
     void testAddPerson() {
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


    @Test
    void getAllPersonsTest() {
        when(personService.getAll()).thenReturn(Stream.of(new Person("adem", "rmdhn", "fresnes", "paris", "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getAll().size());
    }


    @Test
    void deletePersonTest() {
        Person person = (new Person("aaaa", "bbbb", "antony", "paris", "89", "89065454", "aaa@yahoo.com"));
        personService.deletePerson(person);
        // verify(personService, times(1)).deletePerson(person);
    }


    @Test
     void testGetByFirstAndLastName() throws Exception {
        // Données de test
        String firstName = "John";
        String lastName = "Doe";

        // Résultat attendu
        List<PersonInfoDTO> expectedInfo = Arrays.asList(
                new PersonInfoDTO("John", "Doe", "123 Main St", "john@example.com", 30, Arrays.asList("Medication1", "Medication2"), Arrays.asList("Allergy1", "Allergy2")),
                new PersonInfoDTO("John", "Doe", "456 Elm St", "johndoe@example.com", 25, Arrays.asList("Medication3", "Medication4"), Arrays.asList("Allergy3", "Allergy4"))
        );

        // Mock du service
        when(personService.findPersonInfoByFirstAndLastName(anyString(), anyString())).thenReturn(expectedInfo);

        // Exécution de la requête
        mockMvc.perform(get("/v1/api/person/personInfo")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
     void testGetChildAlert() throws Exception {
        // Données de test
        String address = "123 Main St";

        // Résultat attendu
        List<ChildAlertDTO> expectedAlerts = Arrays.asList(
                new ChildAlertDTO("Tenley", "Boyd", 11, Arrays.asList(
                        new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"))),
                        new ChildAlertDTO("Jacob", "Boyd", 32, Arrays.asList(
                                new MedicalRecords("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList())))
                ));

        // Mock du service
        when(personService.getChildrenByAddress(anyString())).thenReturn(expectedAlerts);

        // Exécution de la requête
        mockMvc.perform(get("/v1/api/person/childAlert")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
     void testGetEmailsByCity() throws Exception {
        // Données de test
        String city = "New York";

        // Résultat attendu
        List<String> expectedEmails = Arrays.asList("john@example.com", "johndoe@example.com");

        // Mock du service
        when(personService.getEmailsByCity(anyString())).thenReturn(expectedEmails);

        // Exécution de la requête
        mockMvc.perform(get("/v1/api/person/communityEmail")
                        .param("city", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
     void testFindAllPerson() throws Exception {
       PersonService personService = mock(PersonService.class);
       PersonRepository personRepository = mock(PersonRepository.class);

       List<Person> personList = new ArrayList<>();
       personList.add(new Person());
        when(personRepository.findAll()).thenReturn(personList);

        PersonController personController = new PersonController(personService);
        when(personService.getAll()).thenReturn(personList);
        ResponseEntity<List<Person>> responseEntity= personController.findAllPerson();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personList, responseEntity.getBody());

    }

}

    

