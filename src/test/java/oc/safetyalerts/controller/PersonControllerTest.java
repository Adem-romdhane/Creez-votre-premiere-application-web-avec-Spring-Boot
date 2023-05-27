package oc.safetyalerts.controller;


import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

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
}