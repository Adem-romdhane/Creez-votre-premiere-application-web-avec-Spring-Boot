package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.FireStationsRepository;
import oc.safetyalerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/firestations")
@RequiredArgsConstructor
public class FireStationsController {

   private final FireStationsService fireStationsService;

    @GetMapping("/firestations/all") // READ DATA
    public List<FireStations> findAllFireStations() {
        return fireStationsRepository.findAll();
    }

    @PostMapping("/addFireStation") // add a new FireStation
    public FireStations save (@RequestBody FireStations fireStations) {
        return fireStationsRepository.save(fireStations);
    }

    @PostMapping("/{id}") //update a FireStation
    public ResponseEntity<FireStations> updateFireStations(@PathVariable Long id, @RequestBody FireStations fireStations){
        FireStations fireStationsFinded = fireStationsService.getById(id);
        if (fireStationsFinded == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fireStationsService.updateFirestation(fireStations), HttpStatus.CREATED);

    }

    // Delete fire stations by id
    @DeleteMapping(value = "/deleteStationById/{id}")
    public void deleteStation(@PathVariable Long id){
        fireStationsRepository.deleteById(id);
    }


}
