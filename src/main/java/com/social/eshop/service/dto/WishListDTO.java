package com.social.eshop.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the WishList entity.
 */
public class WishListDTO implements Serializable {

    private Long id;

    private String wishsName;

    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWishsName() {
        return wishsName;
    }

    public void setWishsName(String wishsName) {
        this.wishsName = wishsName;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WishListDTO wishListDTO = (WishListDTO) o;
        if(wishListDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wishListDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WishListDTO{" +
            "id=" + getId() +
            ", wishsName='" + getWishsName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
