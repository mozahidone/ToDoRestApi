package com.mozahidone.graphdb.service;

import com.mozahidone.graphdb.domain.readmodel.Account;
import com.mozahidone.graphdb.domain.readmodel.Payment;
import com.mozahidone.graphdb.repository.AccountRepository;
import com.mozahidone.graphdb.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    public AccountService(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public List<Payment> paymentIn(UUID accountId, UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId).get();

        Account account = accountRepository.findById(accountId).get();
        account.getPayments().add(payment);

        return accountRepository.save(account).getPayments();
    }
}