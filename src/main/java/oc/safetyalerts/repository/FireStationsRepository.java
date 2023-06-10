package oc.safetyalerts.repository;

import oc.safetyalerts.model.FireStations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationsRepository extends JpaRepository<FireStations,Long> {
    List<FireStations> findByStation(int stationNumber);
    List<FireStations> findByAddress(String address);

    List<FireStations> findByStationNumber(int stationNumber);

    List<FireStations> getInfoStation(int stationNumber);
}
