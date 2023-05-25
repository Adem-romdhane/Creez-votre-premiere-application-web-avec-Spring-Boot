package oc.safetyalerts.service;

import oc.safetyalerts.model.Personne;
import oc.safetyalerts.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    @Autowired
    PersonneRepository personneRepository;

    public List<Personne> getAll(){
        return personneRepository.findAll();
    }

    public Personne getById(Long id){
       return personneRepository.findById(id).orElse(null);
    }
}
