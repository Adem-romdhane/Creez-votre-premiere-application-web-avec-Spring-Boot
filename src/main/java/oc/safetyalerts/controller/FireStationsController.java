package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.service.FireStationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/firestations")
@RequiredArgsConstructor
public class FireStationsController {

   private final FireStationsService fireStationsService;
   private List<FireStations> fireStations = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<FireStations>> findAllFireStations() {
        return new ResponseEntity<>(fireStationsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FireStations> save (@RequestBody FireStations fireStations) {
        return new ResponseEntity<>(fireStationsService.savedFireStation(fireStations), HttpStatus.CREATED);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<FireStations> updateFireStations(@PathVariable Long id, @RequestBody FireStations fireStations){
        FireStations fireStationsFinded = fireStationsService.getById(id);
        if (fireStationsFinded == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fireStationsService.updateFirestation(fireStations), HttpStatus.OK);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStationById(@PathVariable Long id){
        FireStations fireStationsFinded = fireStationsService.getById(id);
        if(fireStationsFinded == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
