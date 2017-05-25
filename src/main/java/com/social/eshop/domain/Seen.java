package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Seen.
 */
@Entity
@Table(name = "seen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "seen")
public class Seen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @OneToMany(mappedBy = "seen")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Products> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public Seen data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public Seen products(Set<Products> products) {
        this.products = products;
        return this;
    }

    public Seen addProduct(Products products) {
        this.products.add(products);
        products.setSeen(this);
        return this;
    }

    public Seen removeProduct(Products products) {
        this.products.remove(products);
        products.setSeen(null);
        return this;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seen seen = (Seen) o;
        if (seen.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Seen{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
