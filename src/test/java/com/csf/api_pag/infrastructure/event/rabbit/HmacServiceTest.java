package com.csf.api_pag.infrastructure.event.rabbit;

import com.csf.api_pag.domain.order.event.OrderPaidEvent;
import com.csf.api_pag.domain.shared.valueobjects.ChargeId;
import com.csf.api_pag.domain.shared.valueobjects.CheckoutId;
import com.csf.api_pag.domain.shared.valueobjects.OrderId;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HmacServiceTest {
    private static final String SECRET = "test-secret";

    private static OrderPaidEvent buildEvent(
            UUID orderId,
            UUID checkoutId,
            String gatewayOrderId,
            String customerId,
            List<String> itensId,
            UUID chargeId
    ) {
        return new OrderPaidEvent(
                new OrderId(orderId),
                new CheckoutId(checkoutId),
                gatewayOrderId,
                customerId,
                itensId,
                new ChargeId(chargeId),
                OffsetDateTime.parse("2024-01-01T10:00:00-03:00")
        );
    }

    @Test
    void generateFor_returnsExpectedHmacForStablePayload() {
        // Arrange
        OrderPaidEvent event = buildEvent(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "gw-123",
                "cust-999",
                List.of("item-1", "item-2"),
                UUID.fromString("33333333-3333-3333-3333-333333333333")
        );
        HmacService hmacService = new HmacService(SECRET);
        String expectedPayload = String.join("|",
                "orderId=11111111-1111-1111-1111-111111111111",
                "checkoutId=22222222-2222-2222-2222-222222222222",
                "gatewayOrderId=gw-123",
                "customerId=cust-999",
                "itensId=[item-1, item-2]",
                "chargeId=33333333-3333-3333-3333-333333333333"
        );

        // Act
        String expected = hmacService.hmacSha256(expectedPayload, SECRET);
        String actual = hmacService.generateFor(event);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void generateFor_changesWhenPayloadChanges() {
        // Arrange
        HmacService hmacService = new HmacService(SECRET);
        OrderPaidEvent first = buildEvent(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "gw-123",
                "cust-999",
                List.of("item-1"),
                UUID.fromString("33333333-3333-3333-3333-333333333333")
        );
        OrderPaidEvent second = buildEvent(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "gw-123",
                "cust-999",
                List.of("item-1"),
                UUID.fromString("44444444-4444-4444-4444-444444444444")
        );

        // Act
        String firstSignature = hmacService.generateFor(first);
        String secondSignature = hmacService.generateFor(second);

        // Assert
        assertNotEquals(firstSignature, secondSignature);
    }
}
