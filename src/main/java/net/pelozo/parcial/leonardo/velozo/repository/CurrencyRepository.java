package net.pelozo.parcial.leonardo.velozo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.pelozo.parcial.leonardo.velozo.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
