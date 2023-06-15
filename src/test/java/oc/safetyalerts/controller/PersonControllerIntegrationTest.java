package oc.safetyalerts.controller;

import oc.safetyalerts.repository.IPersonRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class PersonControllerIntegrationTest {

    @Mock
    private IPersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;



}