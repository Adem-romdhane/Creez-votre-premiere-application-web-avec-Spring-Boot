package oc.safetyalerts;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SafetyAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafetyAlertsApplication.class, args);
    }
}



