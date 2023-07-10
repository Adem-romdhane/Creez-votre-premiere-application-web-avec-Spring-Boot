package oc.safetyalerts.repository;

import oc.safetyalerts.model.FireStations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface IFireStationsRepository {
    List<FireStations> findByStation(int stationNumber);
    FireStations findByAddress(String address);

    List<FireStations> findByStationNumber(int stationNumber);


    List<FireStations> findAll();

    String save(FireStations fireStations);


    void deleteFireStationsByAddress(String address);
}
