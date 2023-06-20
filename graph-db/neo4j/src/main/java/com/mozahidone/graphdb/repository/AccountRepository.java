package com.mozahidone.graphdb.repository;

import com.mozahidone.graphdb.domain.readmodel.Account;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface AccountRepository extends Neo4jRepository<Account, UUID> {
}
