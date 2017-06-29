package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Options entity.
 */
public class OptionsDTO implements Serializable {

    private Long id;

    private String metal;

    private String color;

    private String stone;

    private String marking;

    private Double weight;

    private Double size;

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

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStone() {
        return stone;
    }

    public void setStone(String stone) {
        this.stone = stone;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getLength() {
        return length;
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

        OptionsDTO optionsDTO = (OptionsDTO) o;
        if(optionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), optionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OptionsDTO{" +
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
