package com.mozahidone.graphdb.domain.readmodel;

import com.mozahidone.graphdb.domain.writemodel.AccountStatus;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Node
@Data
public class Account {

    @Id
    @GeneratedValue
    private UUID id;
    private int revision;

    private AccountStatus status;

    private String name;
    private String address;
    private BigDecimal balance;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private List<Payment> payments = new ArrayList<>();

}