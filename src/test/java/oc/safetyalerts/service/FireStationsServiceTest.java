package oc.safetyalerts.service;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.model.PersonDTO;
import oc.safetyalerts.repository.FireStationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FireStationsServiceTest {

    @Mock
    private FireStationsRepository fireStationsRepository;

    @InjectMocks
    private FireStationsService fireStationsService;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavedFireStation() {
        // Créer un objet FireStations fictif pour les tests
        FireStations mockFireStations = new FireStations();
        mockFireStations.setId(1L);
        mockFireStations.setAddress("Station 1");

        // Définir le comportement attendu lors de l'appel à la méthode save du repository
        when(fireStationsRepository.save(mockFireStations)).thenReturn(mockFireStations);

        // Appeler la méthode savedFireStation du service
        FireStations savedFireStation = fireStationsService.savedFireStation(mockFireStations);

        // Vérifier que le résultat retourné est le même que l'objet mockFireStations
        assertEquals(mockFireStations, savedFireStation);
    }

    @Test
    void shouldDeleteStationById(){
        Long fireStationId = 1l;

        fireStationsService.deleteFireStationsById(fireStationId);

        verify(fireStationsRepository).deleteById(fireStationId);
    }

    @Test
    public void testGetFireStationInfo() {
        // Création des données de test
        FireStations fireStation = new FireStations();
        fireStation.setId(1L);
        fireStation.setAddress("Fire Station Address");
        fireStation.setStation(1);

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("123 Main St");
        person1.setPhone("555-1234");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Smith");
        person2.setAddress("456 Elm St");
        person2.setPhone("555-5678");

        fireStation.getPersons().add(person1);
        fireStation.getPersons().add(person2);

        // Mocking
        when(fireStationsRepository.findByStation(1)).thenReturn(List.of(fireStation));

        // Appel de la méthode à tester
        List<PersonDTO> personDTOs = fireStationsService.getFireStationInfo(1);

        // Vérification des résultats
        assertEquals(2, ((List<?>) personDTOs).size());

        PersonDTO personDTO1 = personDTOs.get(0);
        assertEquals("John", personDTO1.getFirstName());
        assertEquals("Doe", personDTO1.getLastName());
        assertEquals("123 Main St", personDTO1.getAddress());
        assertEquals("555-1234", personDTO1.getPhone());

        PersonDTO personDTO2 = personDTOs.get(1);
        assertEquals("Jane", personDTO2.getFirstName());
        assertEquals("Smith", personDTO2.getLastName());
        assertEquals("456 Elm St", personDTO2.getAddress());
        assertEquals("555-5678", personDTO2.getPhone());

        // Vérification des appels aux méthodes du repository
        verify(fireStationsRepository, times(1)).findByStation(1);
        verifyNoMoreInteractions(fireStationsRepository);
    }


}