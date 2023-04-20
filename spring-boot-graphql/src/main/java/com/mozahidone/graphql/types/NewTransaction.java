package com.mozahidone.graphql.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewTransaction {
    private Integer id;
    private String accountNumber;
    private String fromAccount;
    private BigDecimal amount;
    private BigDecimal balance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd, MMM, yyyy")
    private LocalDateTime createdOn;
}
