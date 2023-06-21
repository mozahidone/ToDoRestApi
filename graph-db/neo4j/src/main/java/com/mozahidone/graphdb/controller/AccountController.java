package com.mozahidone.graphdb.controller;

import com.mozahidone.graphdb.domain.readmodel.Account;
import com.mozahidone.graphdb.domain.readmodel.Payment;
import com.mozahidone.graphdb.repository.AccountRepository;
import com.mozahidone.graphdb.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {

    final AccountService accountService;
    final AccountRepository accountRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping(value = "/account")
    public ResponseEntity saveAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    public List<Payment> paymentIn(@PathVariable("accountId") UUID accountId, @PathVariable("paymentId") UUID paymentId) {
        return accountService.paymentIn(accountId, paymentId);
    }
}
