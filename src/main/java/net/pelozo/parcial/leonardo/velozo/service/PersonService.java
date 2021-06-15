package net.pelozo.parcial.leonardo.velozo.service;

import net.pelozo.parcial.leonardo.velozo.api.ApiCurrencyExchangeService;
import net.pelozo.parcial.leonardo.velozo.exception.AlreadyInList;
import net.pelozo.parcial.leonardo.velozo.model.PersonConversionDto;
import net.pelozo.parcial.leonardo.velozo.model.Player;
import net.pelozo.parcial.leonardo.velozo.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;

import net.pelozo.parcial.leonardo.velozo.model.Person;
import net.pelozo.parcial.leonardo.velozo.repository.PersonRepository;

@Service
public class PersonService {


    PersonRepository personRepository;
    ApiCurrencyExchangeService api;

    @Autowired
    public PersonService(PersonRepository personRepository, ApiCurrencyExchangeService api) {
        this.personRepository = personRepository;
        this.api = api;
    }

    public List<Person> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        personRepository.delete(getById(id));
    }

    public Person update(Person _person) {

        Optional<Person> person = personRepository.findById(_person.getId());
        if (person.isPresent()) {
            return personRepository.save(_person);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Person addPlayerToRepresentant(Long id, Long idPlayer) {
        Person manager = getById(id);
        Person player = getById(idPlayer);
        //check if they exist and are what they are supposed to be
        if(!(manager instanceof Manager) || !(player instanceof Player) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //check if player already in list
        if(((Manager) manager).getPlayers().contains(player)){
            throw new AlreadyInList(String.format("Player with id: %d", idPlayer));
        }
        //finally add it
        ((Manager) manager).getPlayers().add((Player) player);
        return personRepository.save(manager);
    }


}
