package oc.safetyalerts.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MedicalRecordsJsonReader {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File medicalRecordsJsonFile = new File("src/main/resources/medicalsrecords.json");

        List<MedicalRecords> medicalRecords = objectMapper.readValue(medicalRecordsJsonFile, new TypeReference<List<MedicalRecords>>() {});

        System.out.println(medicalRecords);
    }
}
