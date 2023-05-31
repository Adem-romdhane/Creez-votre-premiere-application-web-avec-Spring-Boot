package oc.safetyalerts.controller;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.repository.PersonRepository;
import oc.safetyalerts.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest
class PersonControllerIntegrationTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testAddPerson() throws Exception {
        // Créer une instance de personne de test
        Person testPerson = new Person();
        testPerson.setFirstName("John Doe");
        testPerson.setLastName("mock");

        // Définir le comportement attendu pour le repository de personne
        when(personRepository.save(any(Person.class))).thenReturn(testPerson);

        // Construire la requête POST avec le corps JSON de la personne
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addPerson")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstname\":\"John Doe\",\"lastname\":30}");

        // Exécuter la requête et vérifier le résultat
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("mock"));

        // Vérifier que la méthode save du repository a été appelée avec la personne
        verify(personRepository).save(testPerson);
    }


    @Test
    @Disabled
    void testUpdatePerson() throws Exception {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main Street";
        String city = "New York";
        String zip = "10001";
        String email = "john.doe@example.com";
        String phone = "555-1234";

        Person updatedPerson = new Person();
        updatedPerson.setFirstName(firstName);
        updatedPerson.setLastName(lastName);
        updatedPerson.setAddress(address);
        updatedPerson.setCity(city);
        updatedPerson.setZip(zip);
        updatedPerson.setEmail(email);
        updatedPerson.setPhone(phone);

     //   personService.addPerson(updatedPerson);

        String requestJson = "{"
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"address\":\"" + address + "\","
                + "\"city\":\"" + city + "\","
                + "\"zip\":\"" + zip + "\","
                + "\"email\":\"" + email + "\","
                + "\"phone\":\"" + phone + "\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Updated..."));
    }
}