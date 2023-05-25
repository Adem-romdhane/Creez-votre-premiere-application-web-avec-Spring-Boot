package oc.safetyalerts.repository;

import oc.safetyalerts.model.FireStations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationsRepository extends JpaRepository<FireStations,Long> {
}
