package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
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

    @Column(name = "metal")
    private String metal;

    @Column(name = "color")
    private String color;

    @Column(name = "stone")
    private String stone;

    @Column(name = "marking")
    private String marking;

    @Column(name = "weight")
    private Double weight;

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

    public String getColor() {
        return color;
    }

    public Options color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStone() {
        return stone;
    }

    public Options stone(String stone) {
        this.stone = stone;
        return this;
    }

    public void setStone(String stone) {
        this.stone = stone;
    }

    public String getMarking() {
        return marking;
    }

    public Options marking(String marking) {
        this.marking = marking;
        return this;
    }

    public void setMarking(String marking) {
        this.marking = marking;
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
            ", metal='" + getMetal() + "'" +
            ", color='" + getColor() + "'" +
            ", stone='" + getStone() + "'" +
            ", marking='" + getMarking() + "'" +
            ", weight='" + getWeight() + "'" +
            ", size='" + getSize() + "'" +
            ", length='" + getLength() + "'" +
            "}";
    }
}
