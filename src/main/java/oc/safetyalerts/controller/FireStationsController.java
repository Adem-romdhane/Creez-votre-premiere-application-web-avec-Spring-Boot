package oc.safetyalerts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("get all fire stations");
        return new ResponseEntity<>(fireStationsService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<FireStations> addFireStations(@RequestBody FireStations fireStations){
        log.info("add fire stations");
        return new ResponseEntity<>(fireStationsService.addFireStations(fireStations),HttpStatus.CREATED);
    }

    //http://localhost:8080/v1/api/firestations/phoneAlert?stationNumber=2
    @GetMapping("/phoneAlert")
    public List<String> getPhoneByStationNumber(@RequestParam("stationNumber") int stationNumber) {
        log.info("get phone by station number");
        return personService.findPhoneByStationNumber(stationNumber);
    }

   // http://localhost:8080/v1/api/firestations/flood/stations?stations=1,2
    @GetMapping("/flood/stations")
    public List<FloodDTO> getFloodStations(@RequestParam("stations") List<Integer> stationNumbers) {
        log.info("get flood by station number");
        return personService.getFloodStations(stationNumbers);
    }

    //http://localhost:8080/v1/api/firestations/firestation?stationNumber=2
    @GetMapping("/firestation")
    public List<PersonStationDTO> getPeopleByFireStation(@RequestParam("stationNumber") int stationNumber) {
        log.info("get people by fire station");
        return personService.findByStationNumber(stationNumber);
    }

    @DeleteMapping("/{address}")
    public ResponseEntity<Void > deleteStationByAddress(@PathVariable String address){
        FireStations fireStationsFinded = fireStationsService.findByAddress(address);
        if(fireStationsFinded == null){
            log.info("deleted");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.error("not deleted");
        fireStationsService.deleteFireStationsByAddress(address);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}