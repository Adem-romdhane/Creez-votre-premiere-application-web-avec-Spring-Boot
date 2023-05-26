package oc.safetyalerts.controller;



import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void getPersonsTest() {
        when(personRepository.findAll()).thenReturn(Stream.
                of(new Person(null,
                        "adem",
                        "rmdhn",
                        "fresnes",
                        "paris",
                        "65", "166363", "@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, personService.getAlls().size());
    }


}