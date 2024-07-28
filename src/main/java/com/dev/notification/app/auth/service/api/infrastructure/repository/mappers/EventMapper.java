package com.dev.notification.app.auth.service.api.infrastructure.repository.mappers;

import com.dev.notification.app.auth.service.api.domain.Event;
import com.dev.notification.app.auth.service.api.infrastructure.repository.models.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventEntity toAggregate(final Event event){
        return new EventEntity(
                event.getIdentifier(),
                event.getAggregateIdentifier(),
                event.getTransaction().json(),
                event.getEventType(),
                event.getCreatedAt()
        );
    }

    public Event fromAggregate(final EventEntity event){
        return Event.restore(
                event.getIdentifier(),
                event.getAggregateIdentifier(),
                event.getEventType(),
                event.getTransaction(),
                event.getCreatedAt()
        );
    }
}
