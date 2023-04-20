package com.mozahidone.graphql.model;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
public class Transaction extends BaseModel{
    private String accountNumber;
    private String fromAccount;
    private BigDecimal amount;
    private BigDecimal balance;
}
