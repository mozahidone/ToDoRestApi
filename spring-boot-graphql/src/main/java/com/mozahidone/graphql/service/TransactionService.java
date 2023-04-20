package com.mozahidone.graphql.service;

import com.mozahidone.graphql.model.Transaction;
import com.mozahidone.graphql.repository.TransactionRepository;
import com.mozahidone.graphql.types.NewTransaction;
import com.mozahidone.graphql.types.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private Sinks.Many<NewTransaction> transactionSink; // autowire the sink bean to use in the mutation resolver

    @Autowired
    private Flux<NewTransaction> transactionEvents; // autowire the flux bean to use in the transactionList publisher

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public NewTransaction newTransaction(TransactionInput transactionInput){
        Transaction mostRecentTransaction = transactionRepository.findTop1ByAccountNumberOrderByCreatedOnDesc(transactionInput.getAccountNumber());
        BigDecimal balance = new BigDecimal("0");
        if(mostRecentTransaction != null){
            balance = transactionInput.getAmount().add(mostRecentTransaction.getBalance());
        }else {
            balance = balance.add(transactionInput.getAmount());
        }
        Transaction transaction =  new Transaction();
        transaction.setAccountNumber(transactionInput.getAccountNumber());
        transaction.setFromAccount(transactionInput.getFromAccount());
        transaction.setAmount(transactionInput.getAmount());
        transaction.setBalance(balance);
        transaction = transactionRepository.save(transaction);

        NewTransaction newTransaction = new NewTransaction(transaction.getId(), transaction.getAccountNumber(), transaction.getFromAccount(), transaction.getAmount(), transaction.getBalance(), transaction.getCreatedOn());

        transactionSink.tryEmitNext(newTransaction);  // put new transaction data in the sink

        return newTransaction;
    }

    @Transactional(readOnly = true)
    public List<NewTransaction> getTransactions(String accountNumber){
        List<NewTransaction> newTransactionList = new ArrayList<>();
        transactionRepository.findTop5ByAccountNumberOrderByCreatedOnDesc(accountNumber).forEach(transaction -> {
            NewTransaction newTransaction = new NewTransaction(transaction.getId(), transaction.getAccountNumber(), transaction.getFromAccount(), transaction.getAmount(), transaction.getBalance(), transaction.getCreatedOn());
            newTransactionList.add(newTransaction);
        });
        return newTransactionList;
    }

    public List<NewTransaction> transactions(String accountNumber) {
        List<NewTransaction> newTransactionList = new ArrayList<>();

        transactionRepository.findTop5ByAccountNumberOrderByCreatedOnDesc(accountNumber).forEach(transaction -> {
            NewTransaction newTransaction = new NewTransaction(transaction.getId(), transaction.getAccountNumber(), transaction.getFromAccount(), transaction.getAmount(), transaction.getBalance(), transaction.getCreatedOn());
            newTransactionList.add(newTransaction);
        });
        return newTransactionList;
    }

    public Flux<NewTransaction> transactionList(String accountNumber) {
        transactionEvents = Flux.fromIterable(getTransactions(accountNumber));
        return transactionEvents;
    }
    
}
