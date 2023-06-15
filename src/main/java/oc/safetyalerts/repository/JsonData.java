package oc.safetyalerts.repository;

import com.google.gson.Gson;
import oc.safetyalerts.model.FireStations;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.model.Person;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class JsonData {

    Gson gson = new Gson();

    private Data readData() {
        Data data = new Data();
        try (
                FileReader reader = new FileReader("src/main/resources/Data.json")) {
            data = gson.fromJson(reader, Data.class);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public List<Person> getPersons() {
        return readData().getPersons();
    }

    public List<FireStations> getFirestations() {
        return readData().getFirestations();
    }

    public List<MedicalRecords> getMedicalRecords() {
        return readData().getMedicalrecords();
    }
}


