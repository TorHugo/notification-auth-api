package com.dev.notification.app.auth.service.api.infrastructure.gateway;

import com.dev.notification.app.auth.service.api.domain.Event;
import com.dev.notification.app.auth.service.api.domain.gateway.EventGateway;
import com.dev.notification.app.auth.service.api.infrastructure.repository.EventRepository;
import com.dev.notification.app.auth.service.api.infrastructure.repository.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventGatewayImpl implements EventGateway {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void save(final Event event) {
        final var entity = eventMapper.toAggregate(event);
        eventRepository.save(entity);
    }
}
