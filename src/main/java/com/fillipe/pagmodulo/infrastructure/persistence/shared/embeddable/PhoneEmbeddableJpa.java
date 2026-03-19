package com.fillipe.pagmodulo.infrastructure.persistence.shared.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneEmbeddableJpa {
    @Column(name = "customer_phone_country")
    private String country;

    @Column(name = "customer_phone_area")
    private String area;

    @Column(name = "customer_phone_number")
    private String number;

    public PhoneEmbeddableJpa() {
    }

    public PhoneEmbeddableJpa(String country, String area, String number) {
        this.country = country;
        this.area = area;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public String getArea() {
        return area;
    }

    public String getNumber() {
        return number;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneEmbeddableJpa{" +
                "country='" + country + '\'' +
                ", area='" + area + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
