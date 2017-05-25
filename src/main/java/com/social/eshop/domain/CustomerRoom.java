package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.social.eshop.domain.enumeration.SocialConnect;

/**
 * A CustomerRoom.
 */
@Entity
@Table(name = "customer_room")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customerroom")
public class CustomerRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sub_scription")
    private Boolean subScription;

    @Enumerated(EnumType.STRING)
    @Column(name = "sosial_connect")
    private SocialConnect sosialConnect;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalInformation personalInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private WishList wishList;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToOne
    @JoinColumn(unique = true)
    private Bucket bucket;

    @OneToOne
    @JoinColumn(unique = true)
    private Seen seen;

    @OneToOne
    @JoinColumn(unique = true)
    private HistoryOrder historyOrder;

    @OneToMany(mappedBy = "customerRoom")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comments> customers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isSubScription() {
        return subScription;
    }

    public CustomerRoom subScription(Boolean subScription) {
        this.subScription = subScription;
        return this;
    }

    public void setSubScription(Boolean subScription) {
        this.subScription = subScription;
    }

    public SocialConnect getSosialConnect() {
        return sosialConnect;
    }

    public CustomerRoom sosialConnect(SocialConnect sosialConnect) {
        this.sosialConnect = sosialConnect;
        return this;
    }

    public void setSosialConnect(SocialConnect sosialConnect) {
        this.sosialConnect = sosialConnect;
    }

    public PersonalInformation getPersonalInfo() {
        return personalInfo;
    }

    public CustomerRoom personalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
        return this;
    }

    public void setPersonalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
    }

    public WishList getWishList() {
        return wishList;
    }

    public CustomerRoom wishList(WishList wishList) {
        this.wishList = wishList;
        return this;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Address getAddress() {
        return address;
    }

    public CustomerRoom address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public CustomerRoom bucket(Bucket bucket) {
        this.bucket = bucket;
        return this;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Seen getSeen() {
        return seen;
    }

    public CustomerRoom seen(Seen seen) {
        this.seen = seen;
        return this;
    }

    public void setSeen(Seen seen) {
        this.seen = seen;
    }

    public HistoryOrder getHistoryOrder() {
        return historyOrder;
    }

    public CustomerRoom historyOrder(HistoryOrder historyOrder) {
        this.historyOrder = historyOrder;
        return this;
    }

    public void setHistoryOrder(HistoryOrder historyOrder) {
        this.historyOrder = historyOrder;
    }

    public Set<Comments> getCustomers() {
        return customers;
    }

    public CustomerRoom customers(Set<Comments> comments) {
        this.customers = comments;
        return this;
    }

    public CustomerRoom addCustomer(Comments comments) {
        this.customers.add(comments);
        comments.setCustomerRoom(this);
        return this;
    }

    public CustomerRoom removeCustomer(Comments comments) {
        this.customers.remove(comments);
        comments.setCustomerRoom(null);
        return this;
    }

    public void setCustomers(Set<Comments> comments) {
        this.customers = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerRoom customerRoom = (CustomerRoom) o;
        if (customerRoom.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerRoom.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerRoom{" +
            "id=" + getId() +
            ", subScription='" + isSubScription() + "'" +
            ", sosialConnect='" + getSosialConnect() + "'" +
            "}";
    }
}
