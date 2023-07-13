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

    private List<FireStations> fireStationsList = new ArrayList<FireStations>();


    @Override
    public List<FireStations> findByAddress(String address) {
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
    public FireStations save(FireStations stations) {
        fireStationsList.add(stations);
        return stations;
    }





    public void delete(FireStations fireStations){
        fireStationsList.remove(fireStations);
    }
}
