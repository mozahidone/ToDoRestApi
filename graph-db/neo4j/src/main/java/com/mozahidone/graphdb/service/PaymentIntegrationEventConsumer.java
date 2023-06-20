package com.mozahidone.graphdb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mozahidone.graphdb.domain.integration.PaymentIntegrationEvent;
import com.mozahidone.graphdb.domain.readmodel.Account;
import com.mozahidone.graphdb.domain.readmodel.Payment;
import com.mozahidone.graphdb.repository.AccountRepository;
import com.mozahidone.graphdb.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentIntegrationEventConsumer {
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @KafkaListener(topics = "account-integration-events", groupId = "account-consumer")
    public void accountListen(String record) {

        try {
            Account account = objectMapper.readValue(record, Account.class);
            log.debug("Received account integration event: {}", account);
            accountRepository.save(account);
        } catch (Exception e) {
            log.error("Error processing account integration event: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "payment-integration-events", groupId = "payment-consumer-neo4j")
    public void paymentListen(String record) {

        try {
            PaymentIntegrationEvent event = objectMapper.readValue(record, PaymentIntegrationEvent.class);
            log.debug("Received payment integration event: {}", event);
            if(event.getEventType().equalsIgnoreCase("PaymentSucceededEvent")) {

                Payment payment = new Payment();
                payment.setId(UUID.randomUUID());
                payment.setPaymentDate(event.getEventTimestamp());
                payment.setAccountId(event.getAccountId());
                payment.setAmount(event.getAmount());
                payment.setCorrelationId(event.getCorrelationId());
                paymentRepository.save(payment);
            }
        } catch (Exception e) {
            log.error("Error processing payment integration event: {}", e.getMessage(), e);
        }
    }
}