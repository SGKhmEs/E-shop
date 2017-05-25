package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.social.eshop.domain.enumeration.Payment;

/**
 * A Confirm.
 */
@Entity
@Table(name = "confirm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "confirm")
public class Confirm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay")
    private Payment pay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPay() {
        return pay;
    }

    public Confirm pay(Payment pay) {
        this.pay = pay;
        return this;
    }

    public void setPay(Payment pay) {
        this.pay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Confirm confirm = (Confirm) o;
        if (confirm.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), confirm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Confirm{" +
            "id=" + getId() +
            ", pay='" + getPay() + "'" +
            "}";
    }
}
