package com.csf.pagmodulo.domain.shared.event;

import java.time.OffsetDateTime;

public interface DomainEvent {
    OffsetDateTime occurredOn();
}
