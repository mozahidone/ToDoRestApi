package com.mozahidone.graphql.controller;

import com.mozahidone.graphql.service.TransactionService;
import com.mozahidone.graphql.types.NewTransaction;
import com.mozahidone.graphql.types.TransactionFilter;
import com.mozahidone.graphql.types.TransactionInput;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
public class TransactionGraphQLController {

    private final TransactionService transactionService;

    public TransactionGraphQLController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @MutationMapping
    public NewTransaction newTransaction(@Argument("input") TransactionInput transactionInput){
        return transactionService.newTransaction(transactionInput);
    }

    @QueryMapping
    public List<NewTransaction> getTransactions(@Argument("filter") TransactionFilter transactionFilter){
        return transactionService.getTransactions(transactionFilter.getAccountNumber());
    }

    //ready to use now from front end
    @SubscriptionMapping
    public Publisher<List<NewTransaction>> transactions(@Argument("filter") final TransactionFilter transactionFilter) {
        return subscriber -> Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            List<NewTransaction> transactions = transactionService.transactions(transactionFilter.getAccountNumber());
            subscriber.onNext(transactions);
        }, 0, 1, TimeUnit.SECONDS);
    }

    //todo this is the next version with major improvements
    @SubscriptionMapping
    public Flux<NewTransaction> getTransactionList(@Argument("filter") TransactionFilter transactionFilter) {
        return transactionService.transactionList(transactionFilter.getAccountNumber());
    }

}
