package com.social.eshop.service.dto;

<<<<<<< HEAD
import com.social.eshop.domain.Seen;
import com.social.eshop.domain.WishList;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class CustomerDTO implements AutoMapping {

    private String sessionId;
    private List<WishList> wishLists;
    private List<Seen> seens;
    private List<ProductsDTO> products;

    public CustomerDTO() { }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public List<Seen> getSeens() {
        return seens;
    }

    public void setSeens(List<Seen> seens) {
        this.seens = seens;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
=======

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Roles;

/**
 * A DTO for the Customer entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private Roles roles;

    private Long loginOptionsId;

    private Long confirmId;

    private Long userRoomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Long getLoginOptionsId() {
        return loginOptionsId;
    }

    public void setLoginOptionsId(Long loginOptionsId) {
        this.loginOptionsId = loginOptionsId;
    }

    public Long getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Long confirmId) {
        this.confirmId = confirmId;
    }

    public Long getUserRoomId() {
        return userRoomId;
    }

    public void setUserRoomId(Long customerRoomId) {
        this.userRoomId = customerRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if(customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
>>>>>>> with_entities
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
<<<<<<< HEAD
            "sessionId='" + sessionId + '\'' +
            ", wishLists=" + wishLists +
            ", seens=" + seens +
            ", products=" + products +
            '}';
=======
            "id=" + getId() +
            ", roles='" + getRoles() + "'" +
            "}";
>>>>>>> with_entities
    }
}
