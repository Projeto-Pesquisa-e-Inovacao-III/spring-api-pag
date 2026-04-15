package com.csf.api_pag.domain.shared.event;


import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent event);
    void publishAll(List<? extends DomainEvent> events);
}
