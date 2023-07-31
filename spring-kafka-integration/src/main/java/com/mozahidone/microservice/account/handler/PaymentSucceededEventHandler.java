package com.mozahidone.microservice.account.handler;

import org.springframework.stereotype.Component;

@Component
public class PaymentSucceededEventHandler {

    /*private final KafkaTemplate<String, PaymentCheckEvent> kafkaTemplate;

    public PaymentSucceededEventHandler(KafkaTemplate<String, PaymentCheckEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = AppConstants.PAYMENT) //PaymentSucceededEvent_topic
    public void handleBalanceUpdateEvent(PaymentSucceededEvent event) {
        // apply payment succeeded event
        apply(event);

        // publish payment check event to the kafka
        PaymentCheckEvent paymentCheckEvent = PaymentCheckEvent.builder()
                        .aggregateId(event.getAggregateId())
                        .amount(event.getPayment().getAmount())
                        .build();
        kafkaTemplate.send(AppConstants.COMPLIANCE, paymentCheckEvent); //PaymentCheckEvent_topic
    }*/
}
