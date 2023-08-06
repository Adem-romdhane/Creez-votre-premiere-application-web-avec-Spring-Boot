package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.IFireStationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FireStationsService {
    private final IFireStationsRepository fireStationsRepository;

    private List<FireStations> fireStations;

    public List<FireStations> getAll() {
        log.info("get all stations");
        return fireStationsRepository.findAll();
    }

    public FireStations findByAddress(String address) {
        log.info("finded by address");
        FireStations fireStations = (FireStations) fireStationsRepository.findByAddress(address);
        return fireStations;
    }

    public void deleteFireStationsByAddress(String address) {
        FireStations fireStations = (FireStations) fireStationsRepository.findByAddress(address);
        if (fireStations != null) {
            log.info("deleted successfully");
            fireStationsRepository.delete(fireStations);
        }
        log.error("not deleted");
    }

    public FireStations addFireStations(FireStations fireStations) {
        log.info("added successfully");
        return fireStationsRepository.save(fireStations);
    }

    public FireStations updateFirestation(FireStations fireStations) {
        log.info("updated successfully");
        FireStations updateFirestation = new FireStations();
        updateFirestation.setAddress(fireStations.getAddress());
        updateFirestation.setStation(fireStations.getStation());

        return fireStationsRepository.save(updateFirestation);
    }

}


