package com.csf.pagmodulo.domain.shared.event;


import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent event);
    void publishAll(List<? extends DomainEvent> events);
}
