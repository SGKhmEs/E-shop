package com.social.eshop.domain;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> with_entities
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.HashSet;
import java.util.Set;
>>>>>>> with_entities
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

<<<<<<< HEAD
    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    private Customer customer;

    @OneToOne
    @JoinColumn(unique = true)
    private Products product;
=======
    @Column(name = "wishs_name")
    private String wishsName;

    @Column(name = "data")
    private LocalDate data;

    @OneToMany(mappedBy = "wishList")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Products> products = new HashSet<>();
>>>>>>> with_entities

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
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

>>>>>>> with_entities
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

<<<<<<< HEAD
    public Customer getCustomer() {
        return customer;
    }

    public WishList customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Products getProduct() {
        return product;
    }

    public WishList product(Products products) {
        this.product = products;
        return this;
    }

    public void setProduct(Products products) {
        this.product = products;
=======
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
>>>>>>> with_entities
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
<<<<<<< HEAD
=======
            ", wishsName='" + getWishsName() + "'" +
>>>>>>> with_entities
            ", data='" + getData() + "'" +
            "}";
    }
}
