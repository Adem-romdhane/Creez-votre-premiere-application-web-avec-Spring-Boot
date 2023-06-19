package oc.safetyalerts.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private IPersonRepository personRepository;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Autowired
    private ObjectMapper objectMapper;


    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Test
    void getAllPersonsTest() {
        when(personService.getAll()).thenReturn(Stream.
                of(new Person(null,
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getAll().size());
    }



    @Test
    void getPersonByAddressTest() {
        String address = "fresnes";
        when(personService.getPersonsByAddress(address)).thenReturn(Stream.
                of(new Person(null,
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getPersonsByAddress(address).size());

    }

    @Test
    void savePersonTest() {
        Person person = (new Person(null,
                "aaaa",
                "bbbb",
                "antony",
                "paris",
                "89", "89065454", "aaa@yahoo.com"));

        when(personService.addPerson(person)).thenReturn(person);
        assertEquals(person, personService.addPerson(person));
    }

    @Test
    void deletePersonTest() {
        Person person = (new Person(null,
                "aaaa",
                "bbbb",
                "antony",
                "paris",
                "89", "89065454", "aaa@yahoo.com"));
        personService.deletePerson(person);
        // verify(personService, times(1)).deletePerson(person);
    }

    @Test
        // test  @GetMapping(value = "/persons/all") from personController
    void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/v1/api/person")).
                andExpect(status().isOk());
    }

   /* void getById() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("adem");

        when(personService.getById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/v1/api/person/1"))
                .andExpect(status().isOk());

    }*/

    @Test
    public void testFindPersonByFirstAndLastName() throws Exception {
        Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");


        when(personService.getByFirstNameAndLastName("John", "Doe")).thenReturn(person);


        // Act & Assert
        mockMvc.perform(get("/v1/api/person/getPersonByName")
                        .param("firstName", "John")
                        .param("lastName", "Doe"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPersonByAddress() throws Exception {
        // List of persons
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "John", "Doe", "123 Street", "City", "12345", "1234567890", "john@example.com"));
        persons.add(new Person(2L, "Jane", "Smith", "456 Avenue", "City", "67890", "9876543210", "jane@example.com"));

        // Configurez le comportement du service pour retourner la liste factice de personnes
        when(personService.getPersonsByAddress("123 Street")).thenReturn(persons);

        // Effectuez la requête GET sur l'endpoint "/address/{address}"
        mockMvc.perform(get("/v1/api/person/address/{address}", "123 Street")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

 /*   @Test
    public void testDeletePersonById() throws Exception {
        // Créez un ID de personne pour le test
        Long personId = 1L;

        // Mockez le résultat du service pour retourner une personne non trouvée (null)
        when(personService.getById(personId)).thenReturn(null);

        // Effectuez la requête DELETE pour supprimer la personne par ID
        mockMvc.perform(delete("/v1/api/person/{id}", personId))
                .andExpect(status().isNotFound());

        // Vérifiez que le service n'a pas été appelé pour supprimer la personne
        verify(personService, never()).deletePerson(any(Person.class));
    }*/

    @Test
    public void testAddPerson() throws Exception {
        // Person to send form data
        String personJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"address\":\"123 Street\",\"city\":\"City\",\"zip\":\"12345\",\"phone\":\"1234567890\",\"email\":\"john.doe@example.com\"}";

        // Create a person instance to send
        Person savedPerson = new Person();
        savedPerson.setId(1L);
        savedPerson.setFirstName("John");
        savedPerson.setLastName("Doe");
        savedPerson.setAddress("123 Street");
        savedPerson.setCity("City");
        savedPerson.setZip("12345");
        savedPerson.setPhone("1234567890");
        savedPerson.setEmail("john.doe@example.com");

        // Definition of simulated human service behaviour
        when(personService.addPerson(Mockito.any(Person.class))).thenReturn(savedPerson);

        // Execute POST request to add a person
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));
    }

    /* test ces 2 lignes :
      personService.deletePerson(personFinded);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     */
    /*@Test
    public void testDeeltePerson() {
        Long id = 1L;
        Person personFinded = new Person();

        when(personService.getById(id)).thenReturn(personFinded);

        ResponseEntity<Void> response = personController.deletePersonById(id);

        if (personFinded == null) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            verify(personService, never()).deletePerson(any(Person.class));
        } else {
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Vérifiez que le code de statut est "204 No Content"
            verify(personService, times(1)).deletePerson(personFinded);
        }
    }*/
}