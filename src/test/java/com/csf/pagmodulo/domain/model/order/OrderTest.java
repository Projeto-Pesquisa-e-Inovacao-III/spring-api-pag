package com.csf.pagmodulo.domain.model.order;

import com.csf.pagmodulo.domain.order.entity.Charge;
import com.csf.pagmodulo.domain.order.entity.Order;
import com.csf.pagmodulo.domain.order.enums.BillingStatus;
import com.csf.pagmodulo.domain.order.event.OrderPaidEvent;
import com.csf.pagmodulo.domain.shared.valueobjects.ChargeId;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import com.csf.pagmodulo.domain.shared.valueobjects.Item;
import com.csf.pagmodulo.domain.shared.valueobjects.Phone;
import com.csf.pagmodulo.domain.shared.valueobjects.TaxDocument;
import com.csf.pagmodulo.domain.shared.valueobjects.TaxDocumentType;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {

    @Test
    void shouldCreatePaidEventWhenChargeStatusIsPaid() {
        Charge paidCharge = aCharge(BillingStatus.PAID, OffsetDateTime.now().minusMinutes(1));
        Order order = newOrderWithCharges(List.of(
                aCharge(BillingStatus.WAITING, null),
                paidCharge
        ));

        List<OrderPaidEvent> paidEvents = order.getPaidEvents();

        assertTrue(order.isPaid());
        assertEquals(1, paidEvents.size());
        assertEquals(paidCharge.getId(), paidEvents.getFirst().chargeId());
    }

    @Test
    void shouldNotCreatePaidEventWhenNoChargeIsPaid() {
        Order order = newOrderWithCharges(List.of(
                aCharge(BillingStatus.WAITING, null),
                aCharge(BillingStatus.IN_ANALYSIS, null)
        ));

        assertFalse(order.isPaid());
        assertTrue(order.getPaidEvents().isEmpty());
    }

    @Test
    void shouldNotCreatePaidEventWhenOrderIsFromExisting() {
        Charge paidCharge = aCharge(BillingStatus.PAID, OffsetDateTime.now().minusMinutes(2));

        Order order = Order.fromExisting()
                .orderId(com.csf.pagmodulo.domain.shared.valueobjects.OrderId.generate())
                .checkoutId(new CheckoutId(UUID.randomUUID()))
                .gatewayOrderId("gw_order_123")
                .createdAt(OffsetDateTime.now().minusHours(1))
                .customer(validCustomer())
                .items(List.of(validItem()))
                .charges(List.of(paidCharge))
                .build();

        assertTrue(order.isPaid());
        assertTrue(order.getPaidEvents().isEmpty());
    }

    private static Order newOrderWithCharges(List<Charge> charges) {
        return Order.newOrder()
                .checkoutId(new CheckoutId(UUID.randomUUID()))
                .gatewayOrderId("gw_order_123")
                .createdAt(OffsetDateTime.now().minusHours(1))
                .customer(validCustomer())
                .items(List.of(validItem()))
                .charges(charges)
                .build();
    }

    private static Charge aCharge(BillingStatus status, OffsetDateTime paidAt) {
        return new Charge(
                new ChargeId(UUID.randomUUID()),
                "gw_charge_123",
                status,
                OffsetDateTime.now().minusHours(1),
                paidAt,
                "charge",
                null,
                null,
                null
        );
    }

    private static Customer validCustomer() {
        return new Customer(
                "1",
                "Joao",
                "joao@example.com",
                new TaxDocument("024.216.140-58", TaxDocumentType.CPF),
                new Phone("+55", "11", "912769028")
        );
    }

    private static Item validItem() {
        return new Item("1", "Produto", "Test produto", 1, 10000);
    }
}

