package com.mozahidone.graphql.repository;

import com.mozahidone.graphql.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop5ByAccountNumberOrderByCreatedOnDesc(String accountNo); // latest five transactions of the given account no.
    Transaction findTop1ByAccountNumberOrderByCreatedOnDesc(String accountNo); // latest transaction of the given account no.
}
