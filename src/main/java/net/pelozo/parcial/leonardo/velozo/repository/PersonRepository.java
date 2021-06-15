package net.pelozo.parcial.leonardo.velozo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.pelozo.parcial.leonardo.velozo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
