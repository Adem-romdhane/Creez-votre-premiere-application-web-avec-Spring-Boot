package oc.safetyalerts.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FireStationsJsonReader {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File fireStationsJsonFile = new File("src/main/resources/firestations.json");

        List<FireStations> fireStations = objectMapper.readValue(fireStationsJsonFile, new TypeReference<List<FireStations>>() {});

        System.out.println(fireStations);
    }
}
