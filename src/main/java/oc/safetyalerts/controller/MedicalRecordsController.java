package oc.safetyalerts.controller;

import oc.safetyalerts.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MedicalRecordsController {

    @Autowired
    MedicalRecordsService medicalRecordsService;
}
