package oc.safetyalerts.service;

import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.IPersonRepository;
import oc.safetyalerts.repository.JsonData;
import oc.safetyalerts.service.dto.*;
import oc.safetyalerts.service.mapper.ChildAlertMapper;
import oc.safetyalerts.service.mapper.PersonStationMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
     void testGetAll() {
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
     void findByStationNumber_ShouldReturnPersonStationDTOList() {
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
     void getChildrenByAddress_ShouldReturnChildAlertDTOList() {
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
     void getPeopleByAddress_ShouldReturnPersonFireAddressDTOList() {
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

    @Test
     void testUpdatePerson() {
        // Création d'un objet Person pour la mise à jour
        Person personToUpdate = new Person();
        personToUpdate.setFirstName("John");
        personToUpdate.setLastName("Doe");
        personToUpdate.setAddress("123 Main St");
        personToUpdate.setCity("City");
        personToUpdate.setZip("12345");
        personToUpdate.setEmail("john.doe@example.com");
        personToUpdate.setPhone("123456789");

        // Création d'un objet Person avec les données mises à jour
        Person updatedPerson = new Person();
        updatedPerson.setFirstName("John");
        updatedPerson.setLastName("dead");
        updatedPerson.setAddress("123 Main St");
        updatedPerson.setCity("City");
        updatedPerson.setZip("12345");
        updatedPerson.setEmail("john.doe@example.com");
        updatedPerson.setPhone("123456789");

        // Définition du comportement attendu du repository mocké
        when(personRepository.save(personToUpdate)).thenReturn(updatedPerson);

        // Appel de la méthode à tester
        Person result = personService.updatePerson(personToUpdate);

        // Vérification du résultat
        assertEquals(updatedPerson, result);
    }

    @Test
     void testAddPerson() {
        // Création d'un objet Person à ajouter
        Person personToAdd = new Person();
        personToAdd.setFirstName("John");
        personToAdd.setLastName("Doe");
        personToAdd.setAddress("123 Main St");
        personToAdd.setCity("City");
        personToAdd.setZip("12345");
        personToAdd.setEmail("john.doe@example.com");
        personToAdd.setPhone("123456789");

        // Définition du comportement attendu du repository mocké
        when(personRepository.save(personToAdd)).thenReturn(personToAdd);

        // Appel de la méthode à tester
        Person result = personService.addPerson(personToAdd);

        // Vérification du résultat
        assertEquals(personToAdd, result);
    }

    @Test
     void testGetFloodStations() {
        // Création d'une liste de numéros de station
        List<Integer> stationNumbers = Arrays.asList(1, 2, 3);

        // Création d'une liste de DTO de Flood pour simuler le résultat attendu
        List<FloodDTO> expectedFloodDTOs = Arrays.asList(
                new FloodDTO("Address 1", Arrays.asList(
                        new PersonInfoMedicalDTO("1234567890", new MedicalRecords("John", "Boyd", "03/06/1984",
                                Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan")))
                )),
                new FloodDTO("Address 2", Arrays.asList(
                        new PersonInfoMedicalDTO("0987654321", new MedicalRecords("Jacob", "Boyd", "03/06/1989",
                                Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), Arrays.asList()))
                )),
                new FloodDTO("Address 3", Arrays.asList(
                        new PersonInfoMedicalDTO("9876543210", new MedicalRecords("Tenley", "Boyd", "02/18/2012",
                                Arrays.asList(), Arrays.asList("peanut")))
                ))
        );

        // Définition du comportement attendu du repository mocké
        when(personRepository.getFloodStations(stationNumbers)).thenReturn(expectedFloodDTOs);

        // Appel de la méthode à tester
        List<FloodDTO> result = personService.getFloodStations(stationNumbers);

        // Vérification du résultat
        assertEquals(expectedFloodDTOs, result);
    }


    @Test
     void testFindPersonInfoByFirstAndLastName() {
        // Préparation des données de test
        String firstName = "John";
        String lastName = "Boyd";

        // Création de la liste de DTO PersonInfoDTO attendue
        List<PersonInfoDTO> expectedPersonInfoDTOs = Arrays.asList(
                new PersonInfoDTO("John", "Boyd", "Address 1", "john@example.com", 37,
                        Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan"))
        );

        // Définition du comportement attendu du repository mocké
        when(personRepository.findPersonInfoByFirstAndLastName(firstName, lastName)).thenReturn(expectedPersonInfoDTOs);

        // Appel de la méthode à tester
        List<PersonInfoDTO> result = personService.findPersonInfoByFirstAndLastName(firstName, lastName);

        // Vérification du résultat
        assertEquals(expectedPersonInfoDTOs, result);
    }


    @Test
     void testFindPhoneByStationNumber() {
        // Préparation des données de test
        int stationNumber = 123;

        // Création de la liste de numéros de téléphone attendue
        List<String> expectedPhoneNumbers = Arrays.asList("1234567890", "9876543210");

        // Définition du comportement attendu du repository mocké
        when(personRepository.findPhoneByStationNumber(stationNumber)).thenReturn(expectedPhoneNumbers);

        // Appel de la méthode à tester
        List<String> result = personService.findPhoneByStationNumber(stationNumber);

        // Vérification du résultat
        assertEquals(expectedPhoneNumbers, result);
    }

    @Test
     void testGetEmailsByCity() {
        // Préparation des données de test
        String city = "New York";

        // Création de la liste d'e-mails attendue
        List<String> expectedEmails = Arrays.asList("john@example.com", "jane@example.com");

        // Définition du comportement attendu du repository mocké
        when(personRepository.getEmailsByCity(city)).thenReturn(expectedEmails);

        // Appel de la méthode à tester
        List<String> result = personService.getEmailsByCity(city);

        // Vérification du résultat
        assertEquals(expectedEmails, result);
    }

    @Test
     void testDeletePerson() {
        // Préparation des données de test
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");

        // Appel de la méthode à tester
        personService.deletePerson(person);

        // Vérification que la méthode delete du repository mocké a été appelée avec la personne spécifiée
        verify(personRepository, Mockito.times(1)).delete(person);
    }
}