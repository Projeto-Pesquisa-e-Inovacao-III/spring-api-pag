package com.fillipe.pagmodulo.infrastructure.persistence.entity;

import com.fillipe.pagmodulo.domain.entity.CheckoutStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "checkout")
public class CheckoutEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String gatewayId;

    @Column(nullable = false)
    private OffsetDateTime expirationDate;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CheckoutStatus status;

    @OneToOne(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerEntityJpa customer;

    @Column(nullable = false)
    private Boolean customerModifiable;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntityJpa> items;

    private Integer additionalAmount;

    private Integer discountAmount;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentMethodEntityJpa> paymentMethods;

    private String softDescriptor;

    private String redirectUrl;
    private String returnUrl;

    @ElementCollection
    @CollectionTable(name = "checkout_notification_urls", joinColumns = @JoinColumn(name = "checkout_id"))
    @Column(name = "url")
    private List<String> notificationUrls;

    @ElementCollection
    @CollectionTable(name = "checkout_payment_notification_urls", joinColumns = @JoinColumn(name = "checkout_id"))
    @Column(name = "url")
    private List<String> paymentNotificationUrls;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinkEntityJpa> links;

    public CheckoutEntityJpa() {}

    public CheckoutEntityJpa(Long id, UUID uuid, String gatewayId, OffsetDateTime expirationDate, OffsetDateTime createdAt, CheckoutStatus status, CustomerEntityJpa customer, Boolean customerModifiable, List<ItemEntityJpa> items, Integer additionalAmount, Integer discountAmount, List<PaymentMethodEntityJpa> paymentMethods, String softDescriptor, String redirectUrl, String returnUrl, List<String> notificationUrls, List<String> paymentNotificationUrls, List<LinkEntityJpa> links) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CheckoutEntityJpa{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", gatewayId='" + gatewayId + '\'' +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", customer=" + customer.hashCode() +
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


    public Long getId() {
        return id;
    }
    public UUID getUuid() {
        return uuid;
    }
    public String getGatewayId() {
        return gatewayId;
    }
    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    public CheckoutStatus getStatus() {
        return status;
    }
    public CustomerEntityJpa getCustomer() {return customer;}
    public Boolean getCustomerModifiable() {
        return customerModifiable;
    }
    public List<ItemEntityJpa> getItems() {
        return items;
    }
    public Integer getAdditionalAmount() {
        return additionalAmount;
    }
    public Integer getDiscountAmount() {
        return discountAmount;
    }
    public List<PaymentMethodEntityJpa> getPaymentMethods() {
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
    public List<LinkEntityJpa> getLinks() {
        return links;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
    public void setExpirationDate(OffsetDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setStatus(CheckoutStatus status) {
        this.status = status;
    }
    public void setCustomer(CustomerEntityJpa customer) {
        this.customer = customer;
    }
    public void setCustomerModifiable(Boolean customerModifiable) {
        this.customerModifiable = customerModifiable;
    }
    public void setItems(List<ItemEntityJpa> items) {
        this.items = items;
    }
    public void setAdditionalAmount(Integer additionalAmount) {
        this.additionalAmount = additionalAmount;
    }
    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
    public void setPaymentMethods(List<PaymentMethodEntityJpa> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    public void setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
    }
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    public void setNotificationUrls(List<String> notificationUrls) {
        this.notificationUrls = notificationUrls;
    }
    public void setPaymentNotificationUrls(List<String> paymentNotificationUrls) { this.paymentNotificationUrls = paymentNotificationUrls; }
    public void setLinks(List<LinkEntityJpa> links) {
        this.links = links;
    }
}

