package oc.safetyalerts;

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


    Person person = new Person(null, "adem", "rmdhn", "avenue de la rép", "frenses", "94", "1244", "@yahoo.com");

}
