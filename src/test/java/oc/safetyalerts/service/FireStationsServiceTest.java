package oc.safetyalerts.service;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


}