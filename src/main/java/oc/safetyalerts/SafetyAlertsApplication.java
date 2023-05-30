package oc.safetyalerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import oc.safetyalerts.model.Person;
import oc.safetyalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafetyAlertsApplication.class, args);
    }



}
