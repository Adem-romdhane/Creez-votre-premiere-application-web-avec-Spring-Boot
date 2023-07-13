package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.service.FireStationsService;
import oc.safetyalerts.service.PersonService;
import oc.safetyalerts.service.dto.FloodDTO;
import oc.safetyalerts.service.dto.PersonStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/firestations")
@RequiredArgsConstructor
public class FireStationsController {
    @Autowired
    PersonService personService;
    @Autowired
    FireStationsService fireStationsService;
    private List<FireStations> fireStations = new ArrayList<>();

    public FireStationsController(FireStationsService fireStationsService) {
        this.fireStationsService=fireStationsService;
    }

    @GetMapping
    public ResponseEntity<List<FireStations>> findAllFireStations() {
        return new ResponseEntity<>(fireStationsService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FireStations> addFireStations(@RequestBody FireStations fireStations){
        return new ResponseEntity<>(fireStationsService.addFireStations(fireStations),HttpStatus.CREATED);
    }



    //http://localhost:8080/v1/api/firestations/phoneAlert?stationNumber=2
    @GetMapping("/phoneAlert")
    public List<String> getPhoneByStationNumber(@RequestParam("stationNumber") int stationNumber) {
        return personService.findPhoneByStationNumber(stationNumber);
    }



   // http://localhost:8080/v1/api/firestations/flood/stations?stations=1,2
    @GetMapping("/flood/stations")
    public List<FloodDTO> getFloodStations(@RequestParam("stations") List<Integer> stationNumbers) {
        return personService.getFloodStations(stationNumbers);
    }

    //http://localhost:8080/v1/api/firestations/firestation?stationNumber=2
    @GetMapping("/firestation")
    public List<PersonStationDTO> getPeopleByFireStation(@RequestParam("stationNumber") int stationNumber) {
        return personService.findByStationNumber(stationNumber);
    }


    @PutMapping("{address}")
    public ResponseEntity<FireStations> updateFireStations(@PathVariable String address, @RequestBody FireStations fireStations) {
        FireStations fireStationsFinded = fireStationsService.findByAddress(address);
        if (fireStationsFinded == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fireStationsFinded.setStation(fireStations.getStation());
        fireStationsFinded.setAddress(fireStations.getAddress());
        fireStationsService.updateFirestation(fireStationsFinded);
        return new ResponseEntity<>(fireStationsFinded, HttpStatus.OK);
    }



    @DeleteMapping("/{address}")
    public ResponseEntity<Void > deleteStationByAddress(@PathVariable String address){
        FireStations fireStationsFinded = fireStationsService.findByAddress(address);
        if(fireStationsFinded == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fireStationsService.deleteFireStationsByAddress(address);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}