package oc.safetyalerts.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest

class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;



    @Test
    public void testFindAllPerson() throws Exception {
        given(personRepository.findAll());
    }

    @Test
    public void newPerson() {
        Person person = new Person(null,
                "romdhane",
                "adem",
                "28 avenue de la république",
                "fresnes",
                "94260",
                "12344",
                "adem@gmail.com");
        personRepository.save(person);

    }


}