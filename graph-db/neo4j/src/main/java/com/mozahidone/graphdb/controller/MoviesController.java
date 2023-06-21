package com.mozahidone.graphdb.controller;

import com.mozahidone.graphdb.domain.readmodel.Movie;
import com.mozahidone.graphdb.domain.readmodel.Person;
import com.mozahidone.graphdb.repository.MovieRepository;
import com.mozahidone.graphdb.repository.PersonRepository;
import com.mozahidone.graphdb.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesController {

    final MovieService service;
    final PersonRepository personRepository;
    final MovieRepository movieRepository;

    public MoviesController(MovieService service, PersonRepository personRepository, MovieRepository movieRepository) {
        this.service = service;
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    // curl http://localhost:8080/acting/Tom%20Hanks/Sleepless%20in%20Seattle -XPOST
    @PostMapping(value = "/acting/{person}/{movie}")
    public List<Person> acting(@PathVariable("person") String person, @PathVariable("movie") String movie) {
        return service.actedIn(person,movie);
    }

    @PostMapping(value = "/person")
    public ResponseEntity savePerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PostMapping(value = "/movie")
    public ResponseEntity saveMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(savedMovie);
    }
}
