package oc.safetyalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.Personne;
import oc.safetyalerts.service.PersonneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonneController.class)
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
    private PersonneService personneService;


    @Test
    public void testPersonAdd(){
    }
}