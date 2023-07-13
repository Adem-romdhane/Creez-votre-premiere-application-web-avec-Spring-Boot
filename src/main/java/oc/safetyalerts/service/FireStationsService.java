package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.IFireStationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireStationsService {

    private final IFireStationsRepository fireStationsRepository;

    private List<FireStations> fireStations;

    public List<FireStations> getAll() {
        return fireStationsRepository.findAll();
    }


    public FireStations findByAddress(String address) {
        FireStations fireStations = (FireStations) fireStationsRepository.findByAddress(address);
        return fireStations;
    }

    public void deleteFireStationsByAddress(String address) {
FireStations fireStations = (FireStations) fireStationsRepository.findByAddress(address);
if(fireStations != null){
    fireStationsRepository.delete(fireStations);
}
    }



    public FireStations addFireStations(FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    public  FireStations updateFirestation(FireStations fireStations) {
        FireStations updateFirestation = new FireStations();
        updateFirestation.setAddress(fireStations.getAddress());
        updateFirestation.setStation(fireStations.getStation());

        return fireStationsRepository.save(updateFirestation);
    }




}


