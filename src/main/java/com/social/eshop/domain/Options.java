package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Options.
 */
@Entity
@Table(name = "options")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "options")
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "color", nullable = false)
    private Integer color;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @NotNull
    @Column(name = "metal", nullable = false)
    private String metal;

    @Column(name = "jhi_size")
    private Double size;

    @Column(name = "length")
    private Integer length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public Options color(Integer color) {
        this.color = color;
        return this;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public Options weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getMetal() {
        return metal;
    }

    public Options metal(String metal) {
        this.metal = metal;
        return this;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Double getSize() {
        return size;
    }

    public Options size(Double size) {
        this.size = size;
        return this;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getLength() {
        return length;
    }

    public Options length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Options options = (Options) o;
        if (options.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), options.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Options{" +
            "id=" + getId() +
            ", color='" + getColor() + "'" +
            ", weight='" + getWeight() + "'" +
            ", metal='" + getMetal() + "'" +
            ", size='" + getSize() + "'" +
            ", length='" + getLength() + "'" +
            "}";
    }
}
