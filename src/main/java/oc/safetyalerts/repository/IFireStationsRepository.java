package oc.safetyalerts.repository;

import oc.safetyalerts.model.FireStations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ComponentScan
public interface IFireStationsRepository {
    List<FireStations> findByStation(int stationNumber);
    List<FireStations> findByAddress(String address);

    List<FireStations> findByStationNumber(int stationNumber);

    List<FireStations> getInfoStation(int stationNumber);

    List<FireStations> findAll();

    FireStations save(FireStations fireStations);

    Optional<FireStations> findById(Long id);

    void deleteById(Long id);
}
