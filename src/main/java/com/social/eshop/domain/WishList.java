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
 * A WishList.
 */
@Entity
@Table(name = "wish_list")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "wishlist")
public class WishList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "wishs_name")
    private String wishsName;

    @Column(name = "data")
    private LocalDate data;

    @OneToMany(mappedBy = "wishList")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Products> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWishsName() {
        return wishsName;
    }

    public WishList wishsName(String wishsName) {
        this.wishsName = wishsName;
        return this;
    }

    public void setWishsName(String wishsName) {
        this.wishsName = wishsName;
    }

    public LocalDate getData() {
        return data;
    }

    public WishList data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public WishList products(Set<Products> products) {
        this.products = products;
        return this;
    }

    public WishList addProduct(Products products) {
        this.products.add(products);
        products.setWishList(this);
        return this;
    }

    public WishList removeProduct(Products products) {
        this.products.remove(products);
        products.setWishList(null);
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
        WishList wishList = (WishList) o;
        if (wishList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wishList.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WishList{" +
            "id=" + getId() +
            ", wishsName='" + getWishsName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
