package oc.safetyalerts.repository;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

@RequiredArgsConstructor
public class FireStationsRepository implements IFireStationsRepository {

    private final JsonData jsonData;

    private List<FireStations> fireStations = new ArrayList<FireStations>();

    @Override
    public List<FireStations> findByStation(int stationNumber) {
        return null;
    }

    @Override
    public FireStations findByAddress(String address) {
        return null;
    }


    @Override
    public List<FireStations> findByStationNumber(int stationNumber) {
        return jsonData.getFirestations()
                .stream()
                .filter(station -> station.getStation() == stationNumber)
                .collect(Collectors.toList());
    }


    @Override
    public List<FireStations> findAll() {
        return jsonData.getFirestations();
    }

    @Override
    public String save(FireStations stations) {
        fireStations.add(stations);
        return "successfully added";
    }


    @Override
    public void deleteFireStationsByAddress(String address) {
        List<FireStations> fireStations = jsonData.getFirestations();
        fireStations.removeIf(f -> f.getAddress().equals(address));
    }
}
