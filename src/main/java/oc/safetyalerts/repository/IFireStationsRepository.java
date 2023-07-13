package oc.safetyalerts.repository;

import oc.safetyalerts.model.FireStations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface IFireStationsRepository {
    List<FireStations> findByAddress(String address);

    List<FireStations> findByStationNumber(int stationNumber);


    List<FireStations> findAll();

    FireStations save(FireStations fireStations);

    void delete (FireStations fireStations);

}
