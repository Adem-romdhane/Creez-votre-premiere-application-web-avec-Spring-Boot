package oc.safetyalerts.service;

import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;

import java.util.List;

@lombok.Data
public class Data {
    private List<Person> persons;
    private List<FireStations> firestations;
    private List<MedicalRecords> medicalrecords;

}
