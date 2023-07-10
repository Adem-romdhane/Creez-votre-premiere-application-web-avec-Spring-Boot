package oc.safetyalerts.repository;

import oc.safetyalerts.model.Person;
import oc.safetyalerts.service.dto.ChildAlertDTO;
import oc.safetyalerts.service.dto.FloodDTO;
import oc.safetyalerts.service.dto.PersonFireAddressDTO;
import oc.safetyalerts.service.dto.PersonInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository {


    Person findByFirstNameAndLastName(String firstName, String lastName);


    List<Person> findAll();

    Person save(Person person);

    Person delete(Person person);

    List<Person> findByStationNumber(int stationNumber);

    List<String> findPhoneByStationNumber(int stationNumber);

    List<String> getEmailsByCity(String city);

    List<ChildAlertDTO> getChildAlert(String address);

    List<PersonInfoDTO> findPersonInfoByFirstAndLastName(String firstName, String lastName);

    List<PersonFireAddressDTO> getPeopleByAddress(String address);



    List<FloodDTO> getFloodStations(List<Integer> stationNumbers);
}
