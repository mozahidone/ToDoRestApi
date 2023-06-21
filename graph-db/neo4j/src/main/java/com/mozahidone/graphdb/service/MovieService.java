package com.mozahidone.graphdb.service;

import com.mozahidone.graphdb.domain.readmodel.Person;
import com.mozahidone.graphdb.repository.MovieRepository;
import org.neo4j.driver.Driver;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.ReactiveNeo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    private final Driver driver;
    private final Neo4jTemplate template;
    private final ReactiveNeo4jTemplate rxTemplate;
    private final MovieRepository repository;

    public MovieService(Driver driver, Neo4jTemplate template, ReactiveNeo4jTemplate rxTemplate, MovieRepository repository) {
        this.driver = driver;
        this.template = template;
        this.rxTemplate = rxTemplate;
        this.repository = repository;
    }

    public int nodeCount(String label) {
        return driver.session().run(String.format("MATCH (n:`%s`) RETURN count(*) as nodes",label)).single().get("nodes").asInt();
    }

    @Transactional
    public List<Person> actedIn(String person, String movie) {
        var m = repository.findByTitle(movie);
        var p = template.findById(person, Person.class);
        m.getCast().add(p.get());
        return repository.save(m).getCast();
    }
}
