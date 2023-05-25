package oc.safetyalerts.controller;

import oc.safetyalerts.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firestations")
public class FireStationsController {

    @Autowired
    FireStationsService fireSationsService;
}
