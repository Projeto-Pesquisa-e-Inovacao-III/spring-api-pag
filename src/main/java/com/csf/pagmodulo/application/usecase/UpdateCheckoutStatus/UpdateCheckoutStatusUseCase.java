package com.csf.pagmodulo.application.usecase.UpdateCheckoutStatus;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.port.out.CheckoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class UpdateCheckoutStatusUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateCheckoutStatusUseCase.class);

    private final CheckoutRepository checkoutRepository;

    public UpdateCheckoutStatusUseCase(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public void execute(UpdateCheckoutStatusCommand cmd) {
        log.info("Iniciando atualização de status do checkout via webhook: {}", cmd);

        Optional<Checkout> checkoutOpt = Optional.empty();

        if (cmd.referenceId() != null && !cmd.referenceId().isBlank()) {
            try {
                UUID uuid = UUID.fromString(cmd.referenceId());
                checkoutOpt = checkoutRepository.findByUuid(uuid);
            } catch (IllegalArgumentException e) {
                log.warn("ReferenceId inválido recebido no webhook: {}", cmd.referenceId());
            }
        }

        if (checkoutOpt.isEmpty() && cmd.id() != null && !cmd.id().isBlank()) {
            checkoutOpt = checkoutRepository.findByGatewayId(cmd.id());
        }

        if (checkoutOpt.isEmpty()) {
            log.warn("Checkout não encontrado. ID: {}, ReferenceID: {}", cmd.id(), cmd.referenceId());
            return;
        }

        Checkout checkout = checkoutOpt.get();
        try {
            // updateStatus aceita String e faz a conversão para Enum internamente
            checkout.updateStatus(cmd.status());
            checkoutRepository.update(checkout);
            log.info("Checkout atualizado com sucesso. ID: {}, Novo Status: {}", checkout.getId(), cmd.status());
        } catch (Exception e) {
            log.error("Erro ao atualizar status do checkout: {}", e.getMessage(), e);
            // Não relançamos para não causar retentativa do webhook
        }
    }

    public record UpdateCheckoutStatusCommand(String id, String referenceId, String status) {}
}

