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
 * A Producers.
 */
@Entity
@Table(name = "producers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "producers")
public class Producers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "producers")
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

    public Producers name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public Producers country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Consignment> getConsignments() {
        return consignments;
    }

    public Producers consignments(Set<Consignment> consignments) {
        this.consignments = consignments;
        return this;
    }

    public Producers addConsignment(Consignment consignment) {
        this.consignments.add(consignment);
        consignment.setProducers(this);
        return this;
    }

    public Producers removeConsignment(Consignment consignment) {
        this.consignments.remove(consignment);
        consignment.setProducers(null);
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
        Producers producers = (Producers) o;
        if (producers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), producers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Producers{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
}
