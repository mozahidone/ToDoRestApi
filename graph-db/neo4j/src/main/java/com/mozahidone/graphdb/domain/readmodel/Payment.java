package com.mozahidone.graphdb.domain.readmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.util.UUID;

@Node
@Data
public class Payment {

  @Id
  @GeneratedValue
  private UUID id;
  private int revision;

  private UUID accountId;

  private UUID correlationId;

  private long paymentDate;

  private BigDecimal amount;

  /*@JsonIgnore
  @Relationship(type = "HAS_PAYMENT", direction = Relationship.Direction.INCOMING)
  private Account account;*/
  /*@Relationship(type = "HAS_PAYMENT", direction = Relationship.Direction.INCOMING)
  private Account account;*/
}