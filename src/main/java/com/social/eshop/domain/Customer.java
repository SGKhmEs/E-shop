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
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customer")
public class Customer implements Serializable {

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

    @Column(name = "session_id")
    private String sessionId;

    @OneToOne
    @JoinColumn(unique = true)
    private LoginOptions loginOptions;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalInformation personalInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private Avatar avatar;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Seen> seens = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WishList> wishLists = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comments> comments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isSubScription() {
        return subScription;
    }

    public Customer subScription(Boolean subScription) {
        this.subScription = subScription;
        return this;
    }

    public void setSubScription(Boolean subScription) {
        this.subScription = subScription;
    }

    public SocialConnect getSosialConnect() {
        return sosialConnect;
    }

    public Customer sosialConnect(SocialConnect sosialConnect) {
        this.sosialConnect = sosialConnect;
        return this;
    }

    public void setSosialConnect(SocialConnect sosialConnect) {
        this.sosialConnect = sosialConnect;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Customer sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LoginOptions getLoginOptions() {
        return loginOptions;
    }

    public Customer loginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
        return this;
    }

    public void setLoginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
    }

    public Address getAddress() {
        return address;
    }

    public Customer address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonalInformation getPersonalInfo() {
        return personalInfo;
    }

    public Customer personalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
        return this;
    }

    public void setPersonalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Customer avatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Set<Seen> getSeens() {
        return seens;
    }

    public Customer seens(Set<Seen> seens) {
        this.seens = seens;
        return this;
    }

    public Customer addSeen(Seen seen) {
        this.seens.add(seen);
        seen.setCustomer(this);
        return this;
    }

    public Customer removeSeen(Seen seen) {
        this.seens.remove(seen);
        seen.setCustomer(null);
        return this;
    }

    public void setSeens(Set<Seen> seens) {
        this.seens = seens;
    }

    public Set<WishList> getWishLists() {
        return wishLists;
    }

    public Customer wishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
        return this;
    }

    public Customer addWishList(WishList wishList) {
        this.wishLists.add(wishList);
        wishList.setCustomer(this);
        return this;
    }

    public Customer removeWishList(WishList wishList) {
        this.wishLists.remove(wishList);
        wishList.setCustomer(null);
        return this;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public Customer comments(Set<Comments> comments) {
        this.comments = comments;
        return this;
    }

    public Customer addComments(Comments comments) {
        this.comments.add(comments);
        comments.setCustomer(this);
        return this;
    }

    public Customer removeComments(Comments comments) {
        this.comments.remove(comments);
        comments.setCustomer(null);
        return this;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", subScription='" + isSubScription() + "'" +
            ", sosialConnect='" + getSosialConnect() + "'" +
            ", sessionId='" + getSessionId() + "'" +
            "}";
    }
}
