package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import oc.safetyalerts.service.dto.PersonInfoDTO;
import oc.safetyalerts.service.mapper.PersonFireAddressMapper;
import oc.safetyalerts.service.mapper.PersonInfoMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonRepository implements IPersonRepository {

    private final JsonData jsonData;

    private final PersonFireAddressMapper mapper2;

    private final PersonInfoMapper mapper;

    @Override
    public List<Person> findByAddress(String address) {
        return null;
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Person> findByAddressIn(List<String> addresses) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return jsonData.getPersons();
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Person delete(Person person) {
        return null;
    }

    @Override
    public List<Person> findByStationNumber(int stationNumber) {

        List<Person> personByStationNumber = new ArrayList<>();
        List<FireStations> fireStations = jsonData.getFirestations();
        List<FireStations> stations = fireStations.stream()
                .filter(station -> station.getStation() == stationNumber)
                .collect(Collectors.toList());
        List<Person> people = jsonData.getPersons();
        for (Person person : people) {
            for (FireStations fireStations1 : stations) {
                if (person.getAddress().equals(fireStations1.getAddress())) {
                    personByStationNumber.add(person);
                }
            }
        }
        return personByStationNumber;
    }

    @Override
    public List<String> findPhoneByStationNumber(List<Person> persons, int stationNumber) {
        List<String> phoneNumbers = new ArrayList<>();
        List<FireStations> fireStations = jsonData.getFirestations();
        List<FireStations> stations = fireStations.stream()
                .filter(station -> station.getStation() == stationNumber)
                .collect(Collectors.toList());
        for (Person person : persons) {
            for (FireStations fireStation : stations) {
                if (person.getAddress().equals(fireStation.getAddress())) {
                    phoneNumbers.add(person.getPhone());
                }
            }
        }
        return phoneNumbers;
    }


    public List<ChildAlertDTO> getChildAlert(String address) {
        List<ChildAlertDTO> persons = new ArrayList<>();
        List<ChildAlertDTO> childAlertDTOList = persons.stream()
                .filter(person -> person.getAddress() == address)
                .collect(Collectors.toList());
        List<MedicalRecords> medicalRecords = jsonData.getMedicalRecords();
        for (MedicalRecords medicalRecords1 : medicalRecords) {
            for (ChildAlertDTO child : persons) {
                if (child.calculateAge() <= 18) {
                    persons.add(child);
                }
            }
        }
        return persons;
    }

    @Override
    public List<PersonInfoDTO> findPersonInfoByFirstAndLastName(String firstName, String lastName) {
        List<Person> people = jsonData.getPersons();
        List<MedicalRecords> medicalRecords = jsonData.getMedicalRecords();
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();

        for (Person person : people) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                for (MedicalRecords medicalRecord : medicalRecords) {
                    if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
                        PersonInfoDTO personInfoDTO = mapper.toDto(person, medicalRecord);
                        personInfoDTOs.add(personInfoDTO);
                    }
                }
            }
        }

        return personInfoDTOs;
    }

    @Override
    public List<PersonFireAddressDTO> getPeopleByAddress(String address) {
        List<Person> people = jsonData.getPersons();
        List<FireStations> fireStations = jsonData.getFirestations();
        List<MedicalRecords> medicalRecords = jsonData.getMedicalRecords();
        List<PersonFireAddressDTO> personFireAddressDTOS = new ArrayList<>();

        for (Person person : people) {
            if (person.getAddress().equals(address)) {
                for (FireStations fireStations1 : fireStations) {
                    if (fireStations1.getAddress().equals(address)) {
                        for (MedicalRecords medicalRecord : medicalRecords) {
                            if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                                PersonFireAddressDTO personFireAddressDTO = mapper2.toDto(person, medicalRecord, fireStations1);
                                personFireAddressDTOS.add(personFireAddressDTO);
                                break; // Sortir de la boucle si le dossier médical est trouvé
                            }
                        }
                    }
                }
            }
        }
        return personFireAddressDTOS;
    }



    public List<String> getEmailsByCity(List<Person> persons, String city) {
        List<String> emails = new ArrayList<>();

        for (Person person : persons) {
            if (person.getCity().equalsIgnoreCase(city)) {
                emails.add(person.getEmail());
            }
        }

        return emails;
    }


}
