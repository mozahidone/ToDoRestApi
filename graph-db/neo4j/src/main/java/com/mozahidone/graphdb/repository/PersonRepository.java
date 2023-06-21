package com.mozahidone.graphdb.repository;

import com.mozahidone.graphdb.domain.readmodel.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// http://localhost:8080/movies
@RepositoryRestResource
public interface PersonRepository extends Neo4jRepository<Person,String> {

}
