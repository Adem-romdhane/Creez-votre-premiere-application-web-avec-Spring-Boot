package oc.safetyalerts.repository;

import oc.safetyalerts.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonneRepository extends JpaRepository<Personne, Long> {



}
