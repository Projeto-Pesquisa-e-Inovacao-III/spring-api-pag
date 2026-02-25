package com.fillipe.pagmodulo.domain.checkout.entity;

import com.fillipe.pagmodulo.domain.checkout.valueobject.Customer;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Item;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Link;
import com.fillipe.pagmodulo.domain.checkout.valueobject.PaymentMethod;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Checkout {
    //TODO: refatorar para deixar generico e não preso ao pagbank
    private final UUID uuid;

    private final String gatewayId;

    private final OffsetDateTime expirationDate;

    private final OffsetDateTime createdAt;

    private CheckoutStatus status;

    private final Customer customer;

    private final Boolean customerModifiable;

    private final List<Item> items;

    private final Integer additionalAmount;

    private final Integer discountAmount;

    private final List<PaymentMethod> paymentMethods;

    private final String softDescriptor;

    private final String redirectUrl;

    private final String returnUrl;

    private final List<String> notificationUrls;

    private final List<String> paymentNotificationUrls;

    private final List<Link> links;

    // Todo: descobrir o pq desse campo, talvez algo com charge?
    // private String origin;

    public boolean isExpired() {
        return !this.expirationDate.isAfter(
                OffsetDateTime.now(ZoneOffset.of("-03:00"))
        );
    }

    public void updateStatus(String status) {
        if(status == null || status.isBlank()){
            throw new IllegalArgumentException("Atualização de status com valor nulo ou em branco é ilegal.");
        }
        this.status = mapCheckoutStatus(status);
    }

    public void updateStatus(CheckoutStatus status) {
        this.status = status;
    }

    public CheckoutStatus mapCheckoutStatus(String value){
        try {
            return CheckoutStatus.valueOf(value);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Checkout status: " + value + "é invalido");
        }
    }


    private Checkout(Builder builder) {
        this.uuid = builder.uuid;
        this.gatewayId = builder.gatewayId;
        this.expirationDate = builder.expirationDate;
        this.createdAt = builder.createdAt;
        this.status = builder.status;
        this.customer = builder.customer;
        this.customerModifiable = builder.customerModifiable;
        this.items = builder.items;
        this.additionalAmount = builder.additionalAmount;
        this.discountAmount = builder.discountAmount;
        this.paymentMethods = builder.paymentMethods;
        this.softDescriptor = builder.softDescriptor;
        this.redirectUrl = builder.redirectUrl;
        this.returnUrl = builder.returnUrl;
        this.notificationUrls = builder.notificationUrls;
        this.paymentNotificationUrls = builder.paymentNotificationUrls;
        this.links = builder.links;
    }

    public static Builder fromExisting() {
        return new Builder();
    }

    public static Builder newCheckout() {
        return new Builder()
                .uuid(UUID.randomUUID())
                .expirationDate(OffsetDateTime.now(ZoneOffset.of("-03:00")).plusHours(1))
                .status(CheckoutStatus.CREATING)
                .customerModifiable(false)
                .links(List.of());
    }

    public UUID getUuid() {
        return uuid;
    }
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
    public Boolean getCustomerModifiable() {
        return customerModifiable;
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
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }
    public String getSoftDescriptor() {
        return softDescriptor;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }
    public String getReturnUrl() {
        return returnUrl;
    }
    public List<String> getNotificationUrls() {
        return notificationUrls;
    }
    public List<String> getPaymentNotificationUrls() {
        return paymentNotificationUrls;
    }
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "uuid=" + uuid +
                ", gatewayId='" + gatewayId + '\'' +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", customer=" + customer +
                ", customerModifiable=" + customerModifiable +
                ", items=" + items +
                ", additionalAmount=" + additionalAmount +
                ", discountAmount=" + discountAmount +
                ", paymentMethods=" + paymentMethods +
                ", softDescriptor='" + softDescriptor + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notificationUrls=" + notificationUrls +
                ", paymentNotificationUrls=" + paymentNotificationUrls +
                ", links=" + links +
                '}';
    }

    public static class Builder {
        private UUID uuid;
        private String gatewayId;
        private OffsetDateTime expirationDate;
        private OffsetDateTime createdAt;
        private CheckoutStatus status;
        private Customer customer;
        private Boolean customerModifiable;
        private List<Item> items;
        private Integer additionalAmount;
        private Integer discountAmount;
        private List<PaymentMethod> paymentMethods;
        private String softDescriptor;
        private String redirectUrl;
        private String returnUrl;
        private List<String> notificationUrls;
        private List<String> paymentNotificationUrls;
        private List<Link> links;

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

        public Builder customerModifiable(Boolean customerModifiable) {
            this.customerModifiable = customerModifiable;
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

        public Builder paymentMethods(List<PaymentMethod> paymentMethods) {
            this.paymentMethods = paymentMethods != null ? new ArrayList<>(paymentMethods) : new ArrayList<>();
            return this;
        }

        public Builder softDescriptor(String softDescriptor) {
            this.softDescriptor = softDescriptor;
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public Builder notificationUrls(List<String> notificationUrls) {
            this.notificationUrls = notificationUrls != null ? new ArrayList<>(notificationUrls) : new ArrayList<>();
            return this;
        }

        public Builder paymentNotificationUrls(List<String> paymentNotificationUrls) {
            this.paymentNotificationUrls = paymentNotificationUrls != null ? new ArrayList<>(paymentNotificationUrls) : new ArrayList<>();
            return this;
        }

        public Builder links(List<Link> links) {
            this.links = links != null ? new ArrayList<>(links) : new ArrayList<>();
            return this;
        }

        public Checkout build() {
            validate();
            return new Checkout(this);
        }

        private void validate() {
            if (customer == null) {
                throw new IllegalStateException("Customer is required");
            }
            if (items == null || items.isEmpty()) {
                throw new IllegalStateException("At least one item is required");
            }
            if (paymentMethods == null || paymentMethods.isEmpty()) {
                throw new IllegalStateException("At least one payment method is required");
            }

            HashSet<PaymentMethod> uniqueMethods = new HashSet<>(paymentMethods);
            if (uniqueMethods.size() != paymentMethods.size()) {
                throw new IllegalStateException("Duplicate payment methods are not allowed");
            }
        }
    }
}
