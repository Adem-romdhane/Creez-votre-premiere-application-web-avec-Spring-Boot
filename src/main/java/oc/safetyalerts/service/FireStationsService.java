package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.IFireStationsRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class FireStationsService {


    private final IFireStationsRepository fireStationsRepository;

    private List<FireStations> fireStations;

    public List<FireStations> getAll() {
        return fireStationsRepository.findAll();
    }

    public String addFireStation(FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }




    public  String updateFirestation(FireStations fireStations) {
        FireStations updateFirestation = new FireStations();
        updateFirestation.setAddress(fireStations.getAddress());
        updateFirestation.setStation(fireStations.getStation());

        return fireStationsRepository.save(updateFirestation);
    }





    public FireStations findByAddress(String address) {
        FireStations fireStations = fireStationsRepository.findByAddress(address);
        return fireStations;
    }

    public void deleteFireStationsByAddress(String address) {
        fireStationsRepository.deleteFireStationsByAddress(address);
    }
}


