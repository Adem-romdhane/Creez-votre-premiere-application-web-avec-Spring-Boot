package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationsController {

    @Autowired
    FireStationsRepository fireStationsRepository;

    @GetMapping("/firestations/all") // READ DATA
    public List<FireStations> findAllFireStations() {
        return fireStationsRepository.findAll();
    }

    @PostMapping("/addFireStation") // add a new FireStation
    public FireStations save (@RequestBody FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    @PostMapping("/updateStation/{id}") //update a FireStation
    public String updateFireStations(@PathVariable Long id,@RequestBody FireStations fireStations){
        FireStations updateFireStations = fireStationsRepository.findById(id).get();
        updateFireStations.setStation(fireStations.getStation());
        updateFireStations.setAddress(fireStations.getAddress());

        fireStationsRepository.save(updateFireStations);

        return "Updated successfully";
    }

    // Delete fire stations by id
    @DeleteMapping(value = "/deleteStationById/{id}")
    public void deleteStation(@PathVariable Long id){
        fireStationsRepository.deleteById(id);
    }


}
