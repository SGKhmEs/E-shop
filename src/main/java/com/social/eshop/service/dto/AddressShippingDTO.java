package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AddressShipping entity.
 */
public class AddressShippingDTO implements Serializable {

    private Long id;

    @NotNull
    private String country;

    @NotNull
    private String city;

    private String state;

    private String region;

    @NotNull
    private String street;

    @NotNull
    private String building;

    private String appartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressShippingDTO addressShippingDTO = (AddressShippingDTO) o;
        if(addressShippingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressShippingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressShippingDTO{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", region='" + getRegion() + "'" +
            ", street='" + getStreet() + "'" +
            ", building='" + getBuilding() + "'" +
            ", appartment='" + getAppartment() + "'" +
            "}";
    }
}
