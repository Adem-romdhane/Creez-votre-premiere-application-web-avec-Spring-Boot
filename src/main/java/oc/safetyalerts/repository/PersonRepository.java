package oc.safetyalerts.repository;

import jakarta.persistence.EntityManager;
import oc.safetyalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAddress(String address);

    Person findByFirstNameAndLastName(String firstName, String lastName);

    public static List<Person> findByAddressIn(List<String> addresses) {
        EntityManager entityManager = null;
        return entityManager.createQuery("SELECT p FROM Person p WHERE p.address IN :addresses", Person.class)
                .setParameter("addresses", addresses)
                .getResultList();
    }
}
