    package oc.safetyalerts.service;

    import oc.safetyalerts.model.FireStations;
    import oc.safetyalerts.repository.IFireStationsRepository;
    import oc.safetyalerts.repository.JsonData;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.*;

    @SpringBootTest
    class FireStationsServiceTest {

        @MockBean
        private IFireStationsRepository fireStationsRepository;

        @MockBean
        private JsonData jsonData;

        @Autowired
        private FireStationsService fireStationsService;

        @Test
        public void testUpdateFirestation() {
            // Données de test
            String address = "123 Main Street";
            int station = 2;
            FireStations fireStations = new FireStations();
            fireStations.setAddress(address);
            fireStations.setStation(station);

            // Mock du comportement du repository
            when(fireStationsRepository.save(any(FireStations.class)))
                    .thenReturn(fireStations);

            // Exécution de la méthode à tester
            FireStations updatedFireStations = fireStationsService.updateFirestation(fireStations);

            // Vérification de l'appel à la méthode save du repository
            verify(fireStationsRepository, times(1)).save(fireStations);

            // Vérification du résultat
            assertEquals(address, updatedFireStations.getAddress());
            assertEquals(station, updatedFireStations.getStation());
        }
    }