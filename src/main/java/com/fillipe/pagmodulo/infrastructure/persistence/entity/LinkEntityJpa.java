package com.fillipe.pagmodulo.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "link")
public class LinkEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rel;
    private String href;
    private String method;

    public LinkEntityJpa() {
    }

    public LinkEntityJpa(Long id, String rel, String href, String method) {
        this.id = id;
        this.rel = rel;
        this.href = href;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }

    public String getMethod() {
        return method;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
