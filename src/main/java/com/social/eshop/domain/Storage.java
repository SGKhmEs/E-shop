package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Storage.
 */
@Entity
@Table(name = "storage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "actualite", nullable = false)
    private Boolean actualite;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "storage")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Consignment> consignments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Storage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActualite() {
        return actualite;
    }

    public Storage actualite(Boolean actualite) {
        this.actualite = actualite;
        return this;
    }

    public void setActualite(Boolean actualite) {
        this.actualite = actualite;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Storage quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Consignment> getConsignments() {
        return consignments;
    }

    public Storage consignments(Set<Consignment> consignments) {
        this.consignments = consignments;
        return this;
    }

    public Storage addConsignment(Consignment consignment) {
        this.consignments.add(consignment);
        consignment.setStorage(this);
        return this;
    }

    public Storage removeConsignment(Consignment consignment) {
        this.consignments.remove(consignment);
        consignment.setStorage(null);
        return this;
    }

    public void setConsignments(Set<Consignment> consignments) {
        this.consignments = consignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Storage storage = (Storage) o;
        if (storage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Storage{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", actualite='" + isActualite() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }
}
