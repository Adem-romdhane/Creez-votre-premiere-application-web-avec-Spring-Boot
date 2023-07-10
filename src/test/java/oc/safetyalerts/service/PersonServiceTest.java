package oc.safetyalerts.service;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import oc.safetyalerts.service.dto.PersonStationDTO;
import oc.safetyalerts.service.mapper.ChildAlertMapper;
import oc.safetyalerts.service.mapper.PersonStationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {
    @MockBean
    private IPersonRepository personRepository;
    @MockBean
    private ChildAlertMapper childAlertMapper;
    @MockBean
    private JsonData jsonData;
    @MockBean
    private PersonStationMapper mapper;

    @Autowired
    private PersonService personService;


    @Test
    public void testGetAll() {
        // Mock data
        List<Person> expectedPersons = Arrays.asList(
                new Person( "John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
                new Person("Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com" )
        );

        // Mock the repository method
        when(personRepository.findAll()).thenReturn(expectedPersons);

        // Call the method being tested
        List<Person> result = personService.getAll();

        // Verify the result
        assertEquals(expectedPersons, result);

        // Verify the repository method was called
        verify(personRepository, times(1)).findAll();
    }



    @Test
    public void findByStationNumber_ShouldReturnPersonStationDTOList() {
        // Prepare mock data
        int stationNumber = 2;
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", "Doe", "123 Main St", "Culver", "8944", "841-874-6512", "john.doe@example.com"));
        personList.add(new Person("Jane", "Smith", "456 Elm St", "Culver", "7451", "841-874-7512", "jane.smith@example.com"));

        List<PersonStationDTO> expectedDTOList = personList.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        // Configure mock repository method
        when(personRepository.findByStationNumber(eq(stationNumber))).thenReturn(personList);

        // Create an instance of the service
        PersonService personService = new PersonService(personRepository, mapper);

        // Call the method being tested
        List<PersonStationDTO> result = personService.findByStationNumber(stationNumber);

        // Verify the result
        assertEquals(expectedDTOList, result);

        // Verify mock interactions
        verify(personRepository, times(1)).findByStationNumber(eq(stationNumber));
    }

    @Test
    public void getChildrenByAddress_ShouldReturnChildAlertDTOList() {
        // Prepare mock data
        String address = "123 Main St";

        List<MedicalRecords> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecords("John", "Doe", "02/10/2010", new ArrayList<>(), new ArrayList<>()));
        medicalRecords.add(new MedicalRecords("Jane", "Smith", "05/20/2012", new ArrayList<>(), new ArrayList<>()));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe", address, "Culver", "8944", "841-874-6512", "john.doe@example.com"));
        persons.add(new Person("Jane", "Smith", address, "Culver", "7451", "841-874-7512", "jane.smith@example.com"));

        List<ChildAlertDTO> expectedDTOList = medicalRecords.stream()
                .map(childAlertMapper::toDto)
                .collect(Collectors.toList());

        // Configure mock repository method
        when(personRepository.getChildAlert(eq(address))).thenReturn(expectedDTOList);

        PersonService personService = new PersonService(personRepository, mapper);

        // Call the method being tested
        List<ChildAlertDTO> result = personService.getChildrenByAddress(address);

        // Verify the result
        assertEquals(expectedDTOList, result);

        // Verify mock interactions
        verify(personRepository, times(1)).getChildAlert(eq(address));
    }

    @Test
    public void getPeopleByAddress_ShouldReturnPersonFireAddressDTOList() {
        // Prepare mock data
        String address = "123 Main St";
        List<PersonFireAddressDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(new PersonFireAddressDTO("John", "Doe", address));
        expectedDTOList.add(new PersonFireAddressDTO("Jane", "Smith", address));

        // Configure mock repository method
        when(personRepository.getPeopleByAddress(eq(address))).thenReturn(expectedDTOList);

        // Create an instance of the service
        PersonService personService = new PersonService(personRepository,mapper);

        // Call the method being tested
        List<PersonFireAddressDTO> result = personService.getPeopleByAddress(address);

        // Verify the result
        assertEquals(expectedDTOList, result);

        // Verify mock interactions
        verify(personRepository, times(1)).getPeopleByAddress(eq(address));
    }
}