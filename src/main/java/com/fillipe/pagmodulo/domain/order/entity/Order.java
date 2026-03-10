package com.fillipe.pagmodulo.domain.order.entity;

import com.fillipe.pagmodulo.domain.shared.valueobjects.CheckoutId;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Item;
import com.fillipe.pagmodulo.domain.shared.valueobjects.OrderId;
import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;

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

    private Order(Builder builder) {
        this.id = builder.orderId;
        this.checkoutId = builder.checkoutId;
        this.gatewayOrderId = builder.gatewayOrderId;
        this.createdAt = builder.createdAt;
        this.customer = builder.customer;
        this.items = builder.items;
        this.charges = builder.charges;
    }

    public static Builder builder() {
        return new Builder();
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
