package net.pelozo.parcial.leonardo.velozo.repository;

import net.pelozo.parcial.leonardo.velozo.model.Birthday;
import net.pelozo.parcial.leonardo.velozo.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

}
