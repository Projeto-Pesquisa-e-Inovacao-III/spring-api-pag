package com.csf.pagmodulo.domain.order.entity;

import com.csf.pagmodulo.domain.order.event.OrderPaidEvent;
import com.csf.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import com.csf.pagmodulo.domain.shared.valueobjects.Item;
import com.csf.pagmodulo.domain.shared.valueobjects.OrderId;
import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final OrderId id;
    private final CheckoutId checkoutId;
    private final String gatewayOrderId;
    private final OffsetDateTime createdAt;
    private final Customer customer;
    private final List<Item> items;
    private final List<Charge> charges;
    private final List<OrderPaidEvent> paidEvents;

    public static Builder newOrder() {
        return new Builder()
                .orderId(OrderId.generate())
                .fromExisting(false);
    }

    public static Builder fromExisting() {
        return new Builder().fromExisting(true);
    }

    private Order(Builder builder) {
        this.id = builder.orderId;
        this.checkoutId = builder.checkoutId;
        this.gatewayOrderId = builder.gatewayOrderId;
        this.createdAt = builder.createdAt;
        this.customer = builder.customer;
        this.items = builder.items;
        this.charges = builder.charges;
        this.paidEvents = new ArrayList<>();

        if (!builder.fromExisting) {
            createPaidEvents();
        }
    }

    private void createPaidEvents() {
        for (Charge charge : charges) {
            if (charge.isPaid()) {
                paidEvents.add(new OrderPaidEvent(
                        id,
                        checkoutId,
                        gatewayOrderId,
                        charge.getId(),
                        charge.getPaid_at(),
                        OffsetDateTime.now()
                ));
            }
        }
    }

    public boolean isPaid() {
        return charges.stream().anyMatch(Charge::isPaid);
    }

    public OrderId getOrderId() {
        return id;
    }

    public CheckoutId getCheckoutId() {
        return checkoutId;
    }

    public String getGatewayOrderId() {
        return gatewayOrderId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public List<OrderPaidEvent> getPaidEvents() {
        return List.copyOf(paidEvents);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", checkoutId=" + checkoutId +
                ", gatewayOrderId='" + gatewayOrderId + '\'' +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                ", items=" + items +
                ", charges=" + charges +
                '}';
    }

    public static class Builder {
        private OrderId orderId;
        private CheckoutId checkoutId;
        private String gatewayOrderId;
        private OffsetDateTime createdAt;
        private Customer customer;
        private List<Item> items;
        private List<Charge> charges;
        private boolean fromExisting;

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder checkoutId(CheckoutId checkoutId) {
            this.checkoutId = checkoutId;
            return this;
        }

        public Builder gatewayOrderId(String gatewayOrderId) {
            this.gatewayOrderId = gatewayOrderId;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder items(List<Item> items) {
            this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
            return this;
        }

        public Builder charges(List<Charge> charges) {
            this.charges = charges != null ? new ArrayList<>(charges) : new ArrayList<>();
            return this;
        }

        public Builder fromExisting(boolean fromExisting) {
            this.fromExisting = fromExisting;
            return this;
        }

        public Order build() {
            validate();
            return new Order(this);
        }

        private void validate() {
            if (orderId == null) {
                throw new InvalidFieldException("orderId", "é obrigatório");
            }
            if (checkoutId == null) {
                throw new InvalidFieldException("checkoutId", "é obrigatório");
            }
            if (gatewayOrderId == null || gatewayOrderId.isBlank()) {
                throw new InvalidFieldException("gatewayOrderId", "é obrigatório e não pode ser vazio");
            }
            if (createdAt == null) {
                throw new InvalidFieldException("createdAt", "é obrigatório");
            }
            if (customer == null) {
                throw new InvalidFieldException("customer", "é obrigatório");
            }
            if (items == null || items.isEmpty()) {
                throw new InvalidFieldException("items", "deve conter pelo menos um item");
            }
            if (charges == null || charges.isEmpty()) {
                throw new InvalidFieldException("charges", "deve conter pelo menos uma cobrança");
            }
        }
    }
}
