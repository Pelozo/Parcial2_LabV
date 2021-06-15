package net.pelozo.parcial.leonardo.velozo.controller;

import net.pelozo.parcial.leonardo.velozo.model.PersonConversionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import net.pelozo.parcial.leonardo.velozo.model.Currency;

import net.pelozo.parcial.leonardo.velozo.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAll();
    }

    @PostMapping
    public Currency addCurrency(@RequestBody Currency currency) {
        return currencyService.save(currency);
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Long id) {
        return currencyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        currencyService.deleteById(id);
    }

    @PutMapping
    public Currency replaceCurrency(@RequestBody Currency currency) {
        return currencyService.update(currency);
    }


}
