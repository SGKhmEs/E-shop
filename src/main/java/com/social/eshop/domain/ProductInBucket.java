package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ProductInBucket.
 */
@Entity
@Table(name = "product_in_bucket")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "productinbucket")
public class ProductInBucket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    private Bucket bucket;

    @ManyToOne
    private Products products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public ProductInBucket bucket(Bucket bucket) {
        this.bucket = bucket;
        return this;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Products getProducts() {
        return products;
    }

    public ProductInBucket products(Products products) {
        this.products = products;
        return this;
    }

    public void setProducts(Products products) {
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
        ProductInBucket productInBucket = (ProductInBucket) o;
        if (productInBucket.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productInBucket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductInBucket{" +
            "id=" + getId() +
            "}";
    }
}
