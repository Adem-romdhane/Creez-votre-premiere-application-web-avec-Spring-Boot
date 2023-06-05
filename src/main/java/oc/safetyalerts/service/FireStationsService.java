package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FireStationsService {

    @Autowired
   private final FireStationsRepository fireStationsRepository;

    public List<FireStations> getAll(){
        return fireStationsRepository.findAll();
    }

    public FireStations savedFireStation(FireStations fireStations){
      return fireStationsRepository.save(fireStations);
    }

    public FireStations getById(Long id){
        return fireStationsRepository.findById(id).orElse(null);
    }

    public void deleteFireStationsById(Long id){
        fireStationsRepository.deleteById(id);
    }


    public FireStations updateFirestation(FireStations fireStations) {
        FireStations updateFirestation = new FireStations();
        updateFirestation.setAddress(fireStations.getAddress());
        updateFirestation.setStation(fireStations.getStation());

        return fireStationsRepository.save(updateFirestation);
    }
}
