package oc.safetyalerts.service;

import oc.safetyalerts.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {

    @Autowired
    PersonneRepository personneRepository;
}
