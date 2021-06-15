package net.pelozo.parcial.leonardo.velozo.controller;

import net.pelozo.parcial.leonardo.velozo.model.PersonConversionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import net.pelozo.parcial.leonardo.velozo.model.Person;

import net.pelozo.parcial.leonardo.velozo.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @PutMapping
    public Person replacePerson(@RequestBody Person person) {
        return personService.update(person);
    }

    @PutMapping("{id}/players/{idPlayer}")
    public Person addPlayerToRepresentant(@PathVariable Long id, @PathVariable Long idPlayer){
        return personService.addPlayerToRepresentant(id, idPlayer);
    }

    // Parcial 2




}
