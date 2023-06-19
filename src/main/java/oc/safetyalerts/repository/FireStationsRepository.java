package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FireStationsRepository implements IFireStationsRepository {

    private final JsonData jsonData;
    @Override
    public List<FireStations> findByStation(int stationNumber) {
        return null;
    }

    @Override
    public List<FireStations> findByAddress(String address) {
        return null;
    }

    @Override
    public List<FireStations> findByStationNumber(int stationNumber) {
        return null;
    }

    @Override
    public List<FireStations> getInfoStation(int stationNumber) {
        return null;
    }

    @Override
    public List<FireStations> findAll() {
        return jsonData.getFirestations();
    }

    @Override
    public FireStations save(FireStations fireStations) {
        return null;
    }

    @Override
    public Optional<FireStations> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
