package oc.safetyalerts.service;

import lombok.RequiredArgsConstructor;
import oc.safetyalerts.model.MedicalRecords;
import oc.safetyalerts.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordsService {

    @Autowired
   private final MedicalRecordsRepository medicalRecordsRepository;

    public List<MedicalRecords> getAll(){return medicalRecordsRepository.findAll();}




}
