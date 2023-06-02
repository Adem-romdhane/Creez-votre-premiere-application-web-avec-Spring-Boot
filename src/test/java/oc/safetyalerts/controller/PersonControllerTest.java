package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;


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

    @Test
    void getById() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("adem");

        when(personService.getById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/v1/api/person/1"))
                .andExpect(status().isOk());

    }

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
        // Créez une liste factice de personnes
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

    @Test
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
    }


}