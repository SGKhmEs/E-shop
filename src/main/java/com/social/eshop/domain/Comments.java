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
 * A Comments.
 */
@Entity
@Table(name = "comments")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "comments")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "comments")
    private String comments;

    @Column(name = "data")
    private ZonedDateTime data;

    @ManyToOne
    private CustomerRoom customerRoom;

    @OneToMany(mappedBy = "comments")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Media> customers = new HashSet<>();

    @ManyToOne
    private Products products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public Comments comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Comments data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public CustomerRoom getCustomerRoom() {
        return customerRoom;
    }

    public Comments customerRoom(CustomerRoom customerRoom) {
        this.customerRoom = customerRoom;
        return this;
    }

    public void setCustomerRoom(CustomerRoom customerRoom) {
        this.customerRoom = customerRoom;
    }

    public Set<Media> getCustomers() {
        return customers;
    }

    public Comments customers(Set<Media> media) {
        this.customers = media;
        return this;
    }

    public Comments addCustomer(Media media) {
        this.customers.add(media);
        media.setComments(this);
        return this;
    }

    public Comments removeCustomer(Media media) {
        this.customers.remove(media);
        media.setComments(null);
        return this;
    }

    public void setCustomers(Set<Media> media) {
        this.customers = media;
    }

    public Products getProducts() {
        return products;
    }

    public Comments products(Products products) {
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
        Comments comments = (Comments) o;
        if (comments.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comments.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comments{" +
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
