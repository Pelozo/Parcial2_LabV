package net.pelozo.parcial.leonardo.velozo.controller;

import net.pelozo.parcial.leonardo.velozo.model.PersonConversionDto;
import net.pelozo.parcial.leonardo.velozo.service.BirthdayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BirthDayControllerTest {

    private BirthdayService birthdayService;

    private BirthdayController birthdayController;

    @BeforeEach
    public void setUp(){
        birthdayService = mock(BirthdayService.class);
        birthdayController = new BirthdayController(birthdayService);
    }


    @Test
    public void getGuestsConversionOKTest(){
        try {
            when(birthdayService.getPersonConversions(any())).thenReturn(aPersonConversionList());
            ResponseEntity<List<PersonConversionDto>> response = birthdayController.pay(aPageable());

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(aPersonConversionList().getContent().get(0).getAmount(), response.getBody().get(0).getAmount());
        } catch (InterruptedException | ParseException | IOException e) {
            fail();
        }

    }


    //ffs
    private static Page<PersonConversionDto> aPersonConversionList(){
        PersonConversionDto b = new PersonConversionDto();
        b.setAmount(20f);
        return new PageImpl(List.of(b));
    }

    public static Pageable aPageable(){
        return PageRequest.of(0,10);
    }
}
