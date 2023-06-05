package oc.safetyalerts.service;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.MedicalRecordsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsServiceTest {
    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;

    @InjectMocks
    private MedicalRecordsService medicalRecordsService;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteById() {
        // Créer un objet MedicalRecords fictif pour les tests
        MedicalRecords mockMedicalRecords = new MedicalRecords();
        mockMedicalRecords.setId(1L);
        mockMedicalRecords.setFirstName("John");

        // Définir l'ID fictif de l'objet MedicalRecords à supprimer
        Long medicalRecordsId = 1L;

        // Appeler la méthode DeleteById du service
        medicalRecordsService.DeleteById(medicalRecordsId);

        // Vérifier que la méthode deleteById du repository a été appelée avec le bon ID
        verify(medicalRecordsRepository).deleteById(medicalRecordsId);
    }
}