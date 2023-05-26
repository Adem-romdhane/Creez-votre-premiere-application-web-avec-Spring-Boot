package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonneControllerTest {

    private static final String END_POINT_PATH;

    static {
        END_POINT_PATH = "/person";
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; //json file
    @MockBean
    private PersonService personService;


    @Test
    public void testPersonAdd(){
    }
}