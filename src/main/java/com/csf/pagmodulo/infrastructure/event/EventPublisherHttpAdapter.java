package com.csf.pagmodulo.infrastructure.event;

import com.csf.pagmodulo.domain.shared.event.DomainEvent;
import com.csf.pagmodulo.domain.shared.event.EventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class EventPublisherHttpAdapter implements EventPublisher {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EventPublisherHttpAdapter.class);

    private final List<String> urls;
    private final RestClient restClient;

    public EventPublisherHttpAdapter(@Value("${event.urls}") List<String> urls) {
        this.urls = urls;
        this.restClient = RestClient.builder().build();
    }

    @Override
    public void publish(DomainEvent event) {
        if (urls == null || urls.isEmpty()) {
            log.warn("Nenhuma URL configurada para publicação de eventos.");
            return;
        }

        urls.forEach(url -> {
            try {
                log.info("Tentando publicar evento para: {}", url);
                restClient.post()
                        .uri(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(event)
                        .retrieve()
                        .toBodilessEntity();
                log.info("Evento publicado com sucesso para: {}", url);
            } catch (RestClientResponseException e) {
                log.error("Erro ao publicar evento para: {}. Motivo: {}", url, e.getMessage());
            }
        });
    }

    @Override
    public void publishAll(List<? extends DomainEvent> events) {
        events.forEach(this::publish);
    }
}
