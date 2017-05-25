package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Bucket.
 */
@Entity
@Table(name = "bucket")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "bucket")
public class Bucket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data")
    private ZonedDateTime data;

    @OneToMany(mappedBy = "bucket")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Products> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Bucket data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public Bucket products(Set<Products> products) {
        this.products = products;
        return this;
    }

    public Bucket addProduct(Products products) {
        this.products.add(products);
        products.setBucket(this);
        return this;
    }

    public Bucket removeProduct(Products products) {
        this.products.remove(products);
        products.setBucket(null);
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
        Bucket bucket = (Bucket) o;
        if (bucket.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bucket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bucket{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
