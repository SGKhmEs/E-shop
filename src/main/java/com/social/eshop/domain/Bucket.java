package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
<<<<<<< HEAD
import java.math.BigDecimal;
=======
>>>>>>> with_entities
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

<<<<<<< HEAD
import com.social.eshop.domain.enumeration.Status;

=======
>>>>>>> with_entities
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

<<<<<<< HEAD
    @Column(name = "name")
    private String name;

    @Column(name = "jhi_date")
    private ZonedDateTime date;

    @Column(name = "sum", precision=10, scale=2)
    private BigDecimal sum;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "count")
    private Integer count;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "consignment_note")
    private String consignmentNote;

    @ManyToOne
    private Manager manager;

    @OneToOne
    @JoinColumn(unique = true)
    private AddressShipping addressShipping;

    @OneToMany(mappedBy = "bucket")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductInBucket> productInBuckets = new HashSet<>();

    @ManyToOne
    private Customer customer;
=======
    @Column(name = "data")
    private ZonedDateTime data;

    @OneToMany(mappedBy = "bucket")
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
    public String getName() {
        return name;
    }

    public Bucket name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
>>>>>>> with_entities
    public ZonedDateTime getData() {
        return data;
=======
    public ZonedDateTime getDate() {
        return date;
>>>>>>> creatingDtos
    }

    public Bucket date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

<<<<<<< HEAD
    public BigDecimal getSum() {
        return sum;
    }

    public Bucket sum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public Bucket orderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCount() {
        return count;
    }

    public Bucket count(Integer count) {
        this.count = count;
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Status getStatus() {
        return status;
    }

    public Bucket status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getConsignmentNote() {
        return consignmentNote;
    }

    public Bucket consignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
        return this;
    }

    public void setConsignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public Manager getManager() {
        return manager;
    }

    public Bucket manager(Manager manager) {
        this.manager = manager;
        return this;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public AddressShipping getAddressShipping() {
        return addressShipping;
    }

    public Bucket addressShipping(AddressShipping addressShipping) {
        this.addressShipping = addressShipping;
        return this;
    }

    public void setAddressShipping(AddressShipping addressShipping) {
        this.addressShipping = addressShipping;
    }

    public Set<ProductInBucket> getProductInBuckets() {
        return productInBuckets;
    }

    public Bucket productInBuckets(Set<ProductInBucket> productInBuckets) {
        this.productInBuckets = productInBuckets;
        return this;
    }

    public Bucket addProductInBucket(ProductInBucket productInBucket) {
        this.productInBuckets.add(productInBucket);
        productInBucket.setBucket(this);
        return this;
    }

    public Bucket removeProductInBucket(ProductInBucket productInBucket) {
        this.productInBuckets.remove(productInBucket);
        productInBucket.setBucket(null);
        return this;
    }

    public void setProductInBuckets(Set<ProductInBucket> productInBuckets) {
        this.productInBuckets = productInBuckets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Bucket customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
=======
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
<<<<<<< HEAD
            ", name='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            ", sum='" + getSum() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", count='" + getCount() + "'" +
            ", status='" + getStatus() + "'" +
            ", consignmentNote='" + getConsignmentNote() + "'" +
=======
            ", data='" + getData() + "'" +
>>>>>>> with_entities
            "}";
    }
}
