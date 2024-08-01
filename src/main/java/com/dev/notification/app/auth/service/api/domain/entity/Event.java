package com.dev.notification.app.auth.service.api.domain.entity;

import com.dev.notification.app.auth.service.api.domain.enums.EventType;
import com.dev.notification.app.auth.service.api.domain.utils.IdentifierUtils;
import com.dev.notification.app.auth.service.api.domain.value.object.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event {

    private String identifier;
    private String aggregateIdentifier;
    private EventType eventType;
    private Transaction transaction;
    private LocalDateTime createdAt;

    private Event(final String identifier,
                  final String aggregateIdentifier,
                  final EventType eventType,
                  final String transaction,
                  final LocalDateTime createdAt){
        this.identifier = identifier;
        this.aggregateIdentifier = aggregateIdentifier;
        this.eventType = eventType;
        this.transaction = new Transaction(transaction);
        this.createdAt = createdAt;
    }

    public static Event create(
            final String aggregateIdentifier,
            final EventType eventType,
            final String transaction
    ){
        return new Event(IdentifierUtils.unique(), aggregateIdentifier, eventType, transaction, LocalDateTime.now());
    }

    public static Event restore(final String identifier, final String aggregateIdentifier, final EventType eventType, final String transaction, final LocalDateTime createdAt){
        return new Event(identifier, aggregateIdentifier, eventType, transaction, createdAt);
    }
}
