package com.mozahidone.graphdb.repository;

import com.mozahidone.graphdb.domain.readmodel.Payment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends Neo4jRepository<Payment, UUID> {

    List<Payment> findTop5ByOrderByPaymentDateDesc();
}