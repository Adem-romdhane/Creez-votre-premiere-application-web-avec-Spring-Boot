package oc.safetyalerts.service;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.IMedicalRecordsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalRecordsServiceTest {
    @MockBean
    private IMedicalRecordsRepository medicalRecordsRepository;

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testFindAll(){
        MedicalRecords medicalRecords1 = new MedicalRecords("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg"), Arrays.asList("hydrapermazol:100mg"));
        MedicalRecords medicalRecords2 = new MedicalRecords("jack", "Boaaa", "03/06/1984", Arrays.asList("aznol:350mg"), Arrays.asList("hydrapermazol:100mg"));
        List<MedicalRecords> medicalRecordsList = Arrays.asList(medicalRecords1,medicalRecords2);

        when(medicalRecordsRepository.findAll()).thenReturn(medicalRecordsList);

        List<MedicalRecords> all = medicalRecordsService.getAll();

        assertEquals(2,all.size());

    }
}