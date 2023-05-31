package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

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

    @Test // test  @GetMapping(value = "/persons/all") from personController
     void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/persons/all")).
                andExpect(status().isOk());
    }

}