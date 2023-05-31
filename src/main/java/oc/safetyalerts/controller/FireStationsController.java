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

    //READ ALL FIRE STATION LIST
    @GetMapping("/firestations/all")
    public List<FireStations> findAllFireStations() {
        return fireStationsRepository.findAll();
    }

    //ADD A NEW FIRE STATION
    @PostMapping("/addFireStation")
    public FireStations saveNewFireStation (@RequestBody FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    //UPDATE DATA OF A FIRE STATION WITH THE ID
    @PostMapping(value = "/updateStation/{id}")
    public String updateFireStationsById(@PathVariable Long id,@RequestBody FireStations fireStations){
        FireStations updateFireStations = fireStationsRepository.findById(id).get();
        updateFireStations.setStation(fireStations.getStation());
        updateFireStations.setAddress(fireStations.getAddress());

        fireStationsRepository.save(updateFireStations);

        return "Updated successfully";
    }

    // DELETE A FIRE STATION OF OUR DATABASE
    @DeleteMapping(value = "/deleteStation/{id}")
    public void deleteStationById(@PathVariable Long id){
        fireStationsRepository.deleteById(id);
    }


}
