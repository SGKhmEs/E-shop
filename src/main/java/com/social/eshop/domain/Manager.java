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
 * A Manager.
 */
@Entity
@Table(name = "manager")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_roles")
    private Roles roles;

    @OneToOne
    @JoinColumn(unique = true)
    private LoginOptions loginOptions;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalInformation personalInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private ManagerAccount managerAccount;

    @OneToOne
    @JoinColumn(unique = true)
    private Avatar avatar;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bucket> managers = new HashSet<>();

    public Manager(Long id) {
        this.id = id;
        this.roles = Roles.ADMIN;
    }

    public Manager() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public Manager roles(Roles roles) {
        this.roles = roles;
        return this;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public LoginOptions getLoginOptions() {
        return loginOptions;
    }

    public Manager loginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
        return this;
    }

    public void setLoginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
    }

    public PersonalInformation getPersonalInfo() {
        return personalInfo;
    }

    public Manager personalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
        return this;
    }

    public void setPersonalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
    }

    public ManagerAccount getManagerAccount() {
        return managerAccount;
    }

    public Manager managerAccount(ManagerAccount managerAccount) {
        this.managerAccount = managerAccount;
        return this;
    }

    public void setManagerAccount(ManagerAccount managerAccount) {
        this.managerAccount = managerAccount;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Manager avatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Address getAddress() {
        return address;
    }

    public Manager address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Bucket> getManagers() {
        return managers;
    }

    public Manager managers(Set<Bucket> buckets) {
        this.managers = buckets;
        return this;
    }

    public Manager addManager(Bucket bucket) {
        this.managers.add(bucket);
        bucket.setManager(this);
        return this;
    }

    public Manager removeManager(Bucket bucket) {
        this.managers.remove(bucket);
        bucket.setManager(null);
        return this;
    }

    public void setManagers(Set<Bucket> buckets) {
        this.managers = buckets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manager manager = (Manager) o;
        if (manager.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manager.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manager{" +
            "id=" + getId() +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
