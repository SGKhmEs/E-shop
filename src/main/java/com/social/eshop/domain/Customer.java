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

import com.social.eshop.domain.enumeration.Roles;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_roles")
    private Roles roles;

    @OneToOne
    @JoinColumn(unique = true)
    private LoginOptions loginOptions;

    @OneToOne
    @JoinColumn(unique = true)
    private Confirm confirm;

    @OneToOne
    @JoinColumn(unique = true)
    private CustomerRoom userRoom;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SessionId> customers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public Customer roles(Roles roles) {
        this.roles = roles;
        return this;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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

    public Confirm getConfirm() {
        return confirm;
    }

    public Customer confirm(Confirm confirm) {
        this.confirm = confirm;
        return this;
    }

    public void setConfirm(Confirm confirm) {
        this.confirm = confirm;
    }

    public CustomerRoom getUserRoom() {
        return userRoom;
    }

    public Customer userRoom(CustomerRoom customerRoom) {
        this.userRoom = customerRoom;
        return this;
    }

    public void setUserRoom(CustomerRoom customerRoom) {
        this.userRoom = customerRoom;
    }

    public Set<SessionId> getCustomers() {
        return customers;
    }

    public Customer customers(Set<SessionId> sessionIds) {
        this.customers = sessionIds;
        return this;
    }

    public Customer addCustomer(SessionId sessionId) {
        this.customers.add(sessionId);
        sessionId.setCustomer(this);
        return this;
    }

    public Customer removeCustomer(SessionId sessionId) {
        this.customers.remove(sessionId);
        sessionId.setCustomer(null);
        return this;
    }

    public void setCustomers(Set<SessionId> sessionIds) {
        this.customers = sessionIds;
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
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
