package net.pelozo.parcial.leonardo.velozo.service;

import lombok.SneakyThrows;
import net.pelozo.parcial.leonardo.velozo.api.ApiCurrencyExchangeService;
import net.pelozo.parcial.leonardo.velozo.model.*;
import net.pelozo.parcial.leonardo.velozo.repository.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BirthdayService {

    BirthdayRepository birthdayRepository;
    ApiCurrencyExchangeService api;

    @Autowired
    public BirthdayService(BirthdayRepository birthdayRepository, ApiCurrencyExchangeService api) {
        this.birthdayRepository = birthdayRepository;
        this.api = api;
    }


    public Page<PersonConversionDto> getPersonConversions(Pageable pageable) throws InterruptedException, ParseException, IOException {
        Page<Birthday> birthdays = birthdayRepository.findAll(pageable);

        Float eurvalue = api.getExchangeEur();
        Float dolValue = api.getExchangeDolar();

        if(eurvalue == -1 || dolValue == -1){
            throw new IOException();
        }

        List<PersonConversionDto> result = new ArrayList<>();

        for(Birthday birthday : birthdays.getContent()){
            for(Person person : birthday.getGuests()) {
                if(person instanceof Player){
                    PersonConversionDto p = new PersonConversionDto();
                    p.setName(person.getName() + " " + person.getLastName());
                    Currency currency = ((Player) person).getPlayerValue();
                    if(currency.getCurrency() == TypeCurrency.EUR){
                        p.setAmount(25000 / eurvalue);
                    }else{
                        p.setAmount(25000 / dolValue);
                    }
                    p.setCurrency(((Player) person).getPlayerValue().getCurrency());
                    result.add(p);
                }

            }
        }
        return new PageImpl<PersonConversionDto>(result, pageable, result.size());
    }
}
