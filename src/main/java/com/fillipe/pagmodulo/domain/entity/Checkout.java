package com.fillipe.pagmodulo.domain.entity;

import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.domain.valueobject.Link;
import com.fillipe.pagmodulo.domain.valueobject.PaymentMethod;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

public class Checkout {

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

    // Todo: validar métodos de pagamento repetidos
    private final List<PaymentMethod> paymentMethods;

    private final String softDescriptor;

    // Todo: saber qual das duas é a verdadeira
    private final String redirectUrl;
    private final String returnUrl;

    private final List<String> notificationUrls;

    private final List<String> paymentNotificationUrls;

    private final List<Link> links;

    // Todo: descobrir o pq desse campo, talvez algo com charge?
    // private String origin;

    public Checkout(UUID uuid, String gatewayId, OffsetDateTime expirationDate, OffsetDateTime createdAt, CheckoutStatus status, Customer customer, Boolean customerModifiable, List<Item> items, Integer additionalAmount, Integer discountAmount, List<PaymentMethod> paymentMethods, String softDescriptor, String redirectUrl, String returnUrl, List<String> notificationUrls, List<String> paymentNotificationUrls, List<Link> links) {
        this.uuid = uuid;
        this.gatewayId = gatewayId;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
        this.status = status;
        this.customer = customer;
        this.customerModifiable = customerModifiable;
        this.items = items;
        this.additionalAmount = additionalAmount;
        this.discountAmount = discountAmount;
        this.paymentMethods = paymentMethods;
        this.softDescriptor = softDescriptor;
        this.redirectUrl = redirectUrl;
        this.returnUrl = returnUrl;
        this.notificationUrls = notificationUrls;
        this.paymentNotificationUrls = paymentNotificationUrls;
        this.links = links;
    }

    public Checkout(Customer customer, List<Item> items, Integer additionalAmount, Integer discountAmount, List<PaymentMethod> paymentMethods, String softDescriptor, String redirectUrl, String returnUrl, List<String> notificationUrls, List<String> paymentNotificationUrls) {
        this.uuid = UUID.randomUUID();
        this.gatewayId = null;
        this.expirationDate = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusHours(1);
        this.createdAt = null;
        this.status = CheckoutStatus.CREATING;
        this.customer = customer;
        this.customerModifiable = false;
        this.items = items;
        this.additionalAmount = additionalAmount;
        this.discountAmount = discountAmount;
        this.paymentMethods = paymentMethods;
        this.softDescriptor = softDescriptor;
        this.redirectUrl = redirectUrl;
        this.returnUrl = returnUrl;
        this.notificationUrls = notificationUrls;
        this.paymentNotificationUrls = paymentNotificationUrls;
        this.links = List.of();
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
}
