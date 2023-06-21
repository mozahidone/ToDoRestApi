package com.mozahidone.graphdb.service;

import com.mozahidone.graphdb.domain.readmodel.Movie;
import com.mozahidone.graphdb.domain.readmodel.Person;
import com.mozahidone.graphdb.repository.MovieRepository;
import com.mozahidone.graphdb.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyService {
    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;

    public MyService(PersonRepository personRepository, MovieRepository movieRepository) {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    public void createData() {
        /*// Create Person nodes
        Person person1 = new Person();
        person1.setName("Alice");
        Person person2 = new Person();
        person2.setName("Bob");

        // Create Movie node
        Movie movie = new Movie();
        movie.setTitle("The Matrix");

        // Establish relationships
        person1.setFriends(Collections.singletonList(person2));
        person2.setFriends(Collections.singletonList(person1));
        //person1.getFriends().add(person2);
        //person2.getFriends().add(person1);

        // Save entities
        personRepository.save(person1);
        personRepository.save(person2);
        movieRepository.save(movie);*/
    }
}

