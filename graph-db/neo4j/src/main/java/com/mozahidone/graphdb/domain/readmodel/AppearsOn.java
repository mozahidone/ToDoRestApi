package com.mozahidone.graphdb.domain.readmodel;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

@Data
@RelationshipProperties
public class AppearsOn {

    @Id @GeneratedValue
    private Long id;
    @Property("account_number")
    private Integer accountNumber;

    @TargetNode
    private Payment payment;
}
