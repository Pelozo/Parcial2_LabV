package net.pelozo.parcial.leonardo.velozo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;

import net.pelozo.parcial.leonardo.velozo.model.Currency;
import net.pelozo.parcial.leonardo.velozo.repository.CurrencyRepository;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getAll() {
        List<Currency> currencys = currencyRepository.findAll();
        return currencys;
    }

    public Currency save(Currency currency) {
        return currencyRepository.save(currency);

    }

    public Currency getById(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        currencyRepository.delete(getById(id));
    }

    public Currency update(Currency _currency) {

        Optional<Currency> currency = currencyRepository.findById(_currency.getId());
        if (currency.isPresent()) {
            return currencyRepository.save(_currency);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
