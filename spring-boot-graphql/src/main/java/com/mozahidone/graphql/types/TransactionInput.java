package com.mozahidone.graphql.types;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionInput {
    private String accountNumber;
    private String fromAccount;
    private BigDecimal amount;
    private BigDecimal balance;
}
