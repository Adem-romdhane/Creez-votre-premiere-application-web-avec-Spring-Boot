package oc.safetyalerts.controller;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestations")
public class FireStationsController {

    @Autowired
    FireStationsRepository fireStationsRepository;

    @GetMapping("/firestations")
    public List<FireStations> findAllFireStations() {
        return fireStationsRepository.findAll();
    }

    @PostMapping
    public FireStations save (@RequestBody FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    @PutMapping(value = "/updateStation")
    public String updateFireStations(@PathVariable Long id,@RequestBody FireStations fireStations){
        FireStations updateFireStations = fireStationsRepository.findById(id).get();
        updateFireStations.setNumberStation(fireStations.getNumberStation());
        updateFireStations.setAddress(fireStations.getAddress());
        updateFireStations.setId(fireStations.getId());

        return "Updated successfully";
    }

    @DeleteMapping(value = "/deleteStation")
    public void deletePerson(@RequestBody FireStations fireStations){
        fireStationsRepository.delete(fireStations);
    }


}
