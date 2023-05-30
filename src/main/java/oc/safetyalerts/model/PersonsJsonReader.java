package oc.safetyalerts.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class PersonsJsonReader {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File personsJsonFile = new File("src/main/resources/persons.json");

       List<Person> persons = objectMapper.readValue(personsJsonFile, new TypeReference<List<Person>>() {});

        System.out.println(persons);
    }
}
