package com.fillipe.pagmodulo.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "gateway.config")
public class GatewayConfig {

    private List<String> notificationWebhookUrls;
    private List<String> paymentNotificationUrls;
    private String softDescriptor;
    private String returnUrl;
    private String redirectUrl;
    private Boolean customerModifiable;

    public Boolean getCustomerModifiable() {return customerModifiable;}

    public void setCustomerModifiable(Boolean customerModifiable) {
        this.customerModifiable = customerModifiable;
    }

    public List<String> getNotificationWebhookUrls() {
        return notificationWebhookUrls;
    }

    public void setNotificationWebhookUrls(List<String> notificationWebhookUrls) {
        this.notificationWebhookUrls = notificationWebhookUrls;
    }

    public List<String> getPaymentNotificationUrls() {
        return paymentNotificationUrls;
    }

    public void setPaymentNotificationUrls(List<String> paymentNotificationUrls) {
        this.paymentNotificationUrls = paymentNotificationUrls;
    }

    public String getSoftDescriptor() {
        return softDescriptor;
    }

    public void setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
