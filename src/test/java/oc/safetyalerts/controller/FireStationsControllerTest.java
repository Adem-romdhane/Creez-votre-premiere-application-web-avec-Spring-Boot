package oc.safetyalerts.controller;

import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FireStationsControllerTest {

    @Autowired
    private FireStationsService fireStationsService;

    @MockBean
    private FireStationsRepository fireStationsRepository;

}