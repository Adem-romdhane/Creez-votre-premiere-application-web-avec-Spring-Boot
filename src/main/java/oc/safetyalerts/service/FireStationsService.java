package oc.safetyalerts.service;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.repository.FireStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationsService {

    @Autowired
    FireStationsRepository fireStationsRepository;

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

}
