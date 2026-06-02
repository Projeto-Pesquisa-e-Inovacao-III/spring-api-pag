package com.csf.api_pag.domain.checkout.entity;

import com.csf.api_pag.domain.checkout.enums.CheckoutStatus;
import com.csf.api_pag.domain.checkout.exception.CheckoutExpiredException;
import com.csf.api_pag.domain.shared.exceptions.InvalidFieldException;
import com.csf.api_pag.domain.checkout.exception.CheckoutInvalidStatusException;
import com.csf.api_pag.domain.shared.valueobjects.Customer;
import com.csf.api_pag.domain.shared.valueobjects.Item;
import com.csf.api_pag.domain.shared.valueobjects.CheckoutId;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Checkout {

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-3);

    private final CheckoutId id;

    private final String gatewayId;

    private final OffsetDateTime expirationDate;

    private final OffsetDateTime createdAt;

    private CheckoutStatus status;

    private final Customer customer;

    private final List<Item> items;

    private final Integer additionalAmount;

    private final Integer discountAmount;


    // Todo: descobrir o pq desse campo, talvez algo com charge?
    // private String origin;

    public boolean isExpired() {
        return !this.expirationDate.isAfter(
                OffsetDateTime.now(ZONE_OFFSET)
        );
    }

    public void updateStatus(String status) {
        if(status == null || status.isBlank()){
            throw new InvalidFieldException("status", "não pode ser nulo ou em branco");
        }
        updateStatus(mapCheckoutStatus(status));
    }

    public void updateStatus(CheckoutStatus status) {
        if(isExpired()) throw new CheckoutExpiredException(id, "updateStatus");
        this.status = status;
    }

    public CheckoutStatus mapCheckoutStatus(String value){
        try {
            return CheckoutStatus.valueOf(value);
        } catch (IllegalArgumentException e){
            throw new InvalidFieldException("status", "status '" + value + "' é desconhecido");
        }
    }

    public void validateCanBeActivated() {
        if (isExpired()) {
            throw new CheckoutExpiredException(id, "ativar");
        }

        if (!this.status.equals(CheckoutStatus.INACTIVE)) {
            throw new CheckoutInvalidStatusException(
                    id,
                    this.status,
                    CheckoutStatus.INACTIVE,
                    "ativar"
            );
        }
    }

    public void validateCanBeInactivated() {
        if (isExpired()) {
            throw new CheckoutExpiredException(id, "inativar");
        }

        if (!this.status.equals(CheckoutStatus.ACTIVE)) {
            throw new CheckoutInvalidStatusException(
                    id,
                    this.status,
                    CheckoutStatus.ACTIVE,
                    "inativar"
            );
        }
    }


    private Checkout(Builder builder) {
        this.id = new CheckoutId(builder.uuid);
        this.gatewayId = builder.gatewayId;
        this.expirationDate = builder.expirationDate;
        this.createdAt = builder.createdAt;
        this.status = builder.status;
        this.customer = builder.customer;
        this.items = builder.items;
        this.additionalAmount = builder.additionalAmount;
        this.discountAmount = builder.discountAmount;
    }

    public static Builder fromExisting() {
        return new Builder();
    }

    public static Builder newCheckout() {
        return new Builder()
                .uuid(UUID.randomUUID())
                .expirationDate(OffsetDateTime.now(ZONE_OFFSET).plusHours(1))
                .status(CheckoutStatus.CREATING);
    }

    public CheckoutId getCheckoutId() {
        return id;
    }
    public UUID getId() {return id.value();}
    public String getGatewayId() { return gatewayId; }
    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    public CheckoutStatus getStatus() {
        return status;
    }
    public Customer getCustomer() {
        return customer;
    }
    public List<Item> getItems() {
        return items;
    }
    public Integer getAdditionalAmount() {
        return additionalAmount;
    }
    public Integer getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", gatewayId='" + gatewayId + '\'' +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", customer=" + customer +
                ", items=" + items +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                '}';
    }

    public static class Builder {
        private UUID uuid;
        private String gatewayId;
        private OffsetDateTime expirationDate;
        private OffsetDateTime createdAt;
        private CheckoutStatus status;
        private Customer customer;
        private List<Item> items;
        private Integer additionalAmount;
        private Integer discountAmount;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder gatewayId(String gatewayId) {
            this.gatewayId = gatewayId;
            return this;
        }

        public Builder expirationDate(OffsetDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder status(CheckoutStatus status) {
            this.status = status;
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

        public Builder additionalAmount(Integer additionalAmount) {
            this.additionalAmount = additionalAmount;
            return this;
        }

        public Builder discountAmount(Integer discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }



        public Checkout build() {
            validate();
            return new Checkout(this);
        }

        private void validate() {
            if (customer == null) {
                throw new InvalidFieldException("customer", "é obrigatório");
            }
            if (items == null || items.isEmpty()) {
                throw new InvalidFieldException("items", "deve conter pelo menos um item");
            }
        }
    }
}
