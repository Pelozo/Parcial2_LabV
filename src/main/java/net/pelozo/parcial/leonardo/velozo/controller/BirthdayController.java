package net.pelozo.parcial.leonardo.velozo.controller;

import net.pelozo.parcial.leonardo.velozo.model.PersonConversionDto;
import net.pelozo.parcial.leonardo.velozo.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/birthday")
public class BirthdayController {


    private BirthdayService personService;

    @Autowired
    public BirthdayController(BirthdayService personService) {
        this.personService = personService;
    }

    @GetMapping("/pay")
    public ResponseEntity<List<PersonConversionDto>> pay(Pageable pageable) throws InterruptedException, ParseException, IOException {
        Page<PersonConversionDto> result = personService.getPersonConversions(pageable);
        return response(result);
    }

    public static ResponseEntity response(Page page){
        return ResponseEntity.status(page.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK)
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .header("X-Total-Content",String.valueOf(page.getTotalElements()))
                .body(page.getContent());
    }

}
