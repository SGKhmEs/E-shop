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

    @Column(name = "session_id")
    private String sessionId;

    @OneToOne
    @JoinColumn(unique = true)
    private LoginOptions loginOptions;

    @OneToOne
    @JoinColumn(unique = true)
    private CustomerRoom customerRoom;

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

    public CustomerRoom getCustomerRoom() {
        return customerRoom;
    }

    public Customer customerRoom(CustomerRoom customerRoom) {
        this.customerRoom = customerRoom;
        return this;
    }

    public void setCustomerRoom(CustomerRoom customerRoom) {
        this.customerRoom = customerRoom;
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
            ", sessionId='" + getSessionId() + "'" +
            "}";
    }
}
