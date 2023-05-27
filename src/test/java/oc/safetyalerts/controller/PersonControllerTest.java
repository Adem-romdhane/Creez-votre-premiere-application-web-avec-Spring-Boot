package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPersonsTest() {
        when(personRepository.findAll()).thenReturn(Stream.
                of(new Person(null,
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getAlls().size());
    }


    @Test
    public void getPersonByAdressTest() {
        String address = "fresnes";
        when(personRepository.findByAddress(address)).thenReturn(Stream.
                of(new Person(null,
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getPersonsByAdress(address).size());

    }

    @Test
    public void savePersonTest() {
        Person person = (new Person(null,
                "aaaa",
                "bbbb",
                "antony",
                "paris",
                "89", "89065454", "aaa@yahoo.com"));

        when(personRepository.save(person)).thenReturn(person);
        assertEquals(person, personService.addPerson(person));
    }

    @Test
    public void deletePersonTest() {
        Person person = (new Person(null,
                "aaaa",
                "bbbb",
                "antony",
                "paris",
                "89", "89065454", "aaa@yahoo.com"));
        personService.deletePerson(person);
        verify(personRepository, times(1)).delete(person);
    }

    @Test
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/")).
                andExpect(status().isOk());
    }

    @Test // test  @GetMapping(value = "/persons/all") from personController
    public void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/persons/all")).
                andExpect(status().isOk());
    }


}