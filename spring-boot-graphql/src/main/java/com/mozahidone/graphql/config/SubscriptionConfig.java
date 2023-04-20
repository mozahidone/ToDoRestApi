package com.mozahidone.graphql.config;

import com.mozahidone.graphql.types.NewTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

@Configuration
public class SubscriptionConfig {

    @Bean //the sink, where data goes in
    public Sinks.Many<NewTransaction> transactionSink() {
        return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    @Bean //the flux, where data comes out
    public Flux<NewTransaction> transactionFlux(Sinks.Many<NewTransaction> transactionSink) {
        return transactionSink.asFlux();
    }

}
